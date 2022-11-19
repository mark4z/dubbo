/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.dubbo.registry.xds.util.protocol.impl;

import com.google.protobuf.Any;
import com.google.protobuf.InvalidProtocolBufferException;
import dubbo.networking.v1alpha1.Snp;
import io.envoyproxy.envoy.config.core.v3.Node;
import io.envoyproxy.envoy.service.discovery.v3.AggregatedDiscoveryServiceGrpc;
import io.envoyproxy.envoy.service.discovery.v3.DiscoveryRequest;
import io.envoyproxy.envoy.service.discovery.v3.DiscoveryResponse;
import io.grpc.stub.StreamObserver;
import org.apache.dubbo.common.logger.ErrorTypeAwareLogger;
import org.apache.dubbo.common.logger.LoggerFactory;
import org.apache.dubbo.registry.xds.util.XdsChannel;
import org.apache.dubbo.registry.xds.util.protocol.AbstractProtocol;
import org.apache.dubbo.registry.xds.util.protocol.delta.DeltaSnp;

import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.function.Consumer;
import java.util.stream.Collectors;


public class SnpProtocol extends AbstractProtocol<List<Snp.ServiceMappingXdsResponse>, DeltaSnp> {

    private static final ErrorTypeAwareLogger logger = LoggerFactory.getErrorTypeAwareLogger(SnpProtocol.class);

    protected final XdsChannel xdsChannel;

    protected final Node node;
    protected final AggregatedDiscoveryServiceGrpc.AggregatedDiscoveryServiceStub aggregatedDiscoveryServiceStub;

    private final Set<String> resourceNames = new HashSet<>();
    private final StreamObserver<DiscoveryRequest> discoveryRequestStreamObserver;

    private final Set<Consumer<List<Snp.ServiceMappingXdsResponse>>> listeners = new HashSet<>();

    public SnpProtocol(XdsChannel xdsChannel, Node node, int pollingPoolSize, int pollingTimeout) {
        super(xdsChannel, node, pollingPoolSize, pollingTimeout);
        this.xdsChannel = xdsChannel;
        this.node = node;
        this.aggregatedDiscoveryServiceStub = AggregatedDiscoveryServiceGrpc.newStub(xdsChannel.getChannel());
        this.discoveryRequestStreamObserver = aggregatedDiscoveryServiceStub.streamAggregatedResources(new SnpResponseObserver(this.listeners));
    }

    @Override
    public String getTypeUrl() {
        return "dubbo.networking.v1alpha1.v1.servicenamemapping";
    }

    @Override
    protected List<Snp.ServiceMappingXdsResponse> decodeDiscoveryResponse(DiscoveryResponse response) {
        if (getTypeUrl().equals(response.getTypeUrl())) {
            List<Snp.ServiceMappingXdsResponse> map = response.getResourcesList().stream()
                .map(SnpProtocol::unpackSnpConfiguration)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
            return map;
        }
        return null;
    }

    private static Snp.ServiceMappingXdsResponse unpackSnpConfiguration(Any any) {
        try {
            return any.unpack(Snp.ServiceMappingXdsResponse.class);
        } catch (InvalidProtocolBufferException e) {
            System.out.println("Error occur when decode xDS response.");
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Snp.ServiceMappingXdsResponse> getResource(Set<String> resourceNames) {
        CompletableFuture<List<Snp.ServiceMappingXdsResponse>> future = new CompletableFuture<>();
        StreamObserver<DiscoveryRequest> streamObserver = this.aggregatedDiscoveryServiceStub.streamAggregatedResources(new StreamObserver<DiscoveryResponse>() {
            @Override
            public void onNext(DiscoveryResponse discoveryResponse) {
                List<Snp.ServiceMappingXdsResponse> res = decodeDiscoveryResponse(discoveryResponse);
                System.out.println("snp[inner] once: " + res);
                future.complete(res);
            }

            @Override
            public void onError(Throwable throwable) {
                future.completeExceptionally(throwable);
            }

            @Override
            public void onCompleted() {
                // do nothing
            }
        });
        streamObserver.onNext(buildDiscoveryRequest(resourceNames));
        try {
            return future.get();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public long observeResource(Set<String> resourceNames, Consumer<List<Snp.ServiceMappingXdsResponse>> consumer) {
        this.resourceNames.addAll(resourceNames);
        //sub
        discoveryRequestStreamObserver.onNext(buildDiscoveryRequest(this.resourceNames));
        return 0;
    }

    protected class SnpResponseObserver implements StreamObserver<DiscoveryResponse> {
        private final Set<Consumer<List<Snp.ServiceMappingXdsResponse>>> listeners;

        public SnpResponseObserver(Set<Consumer<List<Snp.ServiceMappingXdsResponse>>> listeners) {
            this.listeners = listeners;
        }

        @Override
        public void onNext(DiscoveryResponse value) {
            logger.info("receive notification from xds server, type: " + getTypeUrl());
            List<Snp.ServiceMappingXdsResponse> result = decodeDiscoveryResponse(value);
            for (Consumer<List<Snp.ServiceMappingXdsResponse>> listener : listeners) {
                listener.accept(result);
            }
        }

        @Override
        public void onError(Throwable t) {
            logger.error("xDS Client received error message! detail:", t);
        }

        @Override
        public void onCompleted() {
            // ignore
        }


    }
}
