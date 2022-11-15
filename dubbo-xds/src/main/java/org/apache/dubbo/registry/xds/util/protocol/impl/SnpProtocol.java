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
import io.envoyproxy.envoy.config.core.v3.Node;
import io.envoyproxy.envoy.service.discovery.v3.DiscoveryResponse;
import org.apache.dubbo.common.logger.ErrorTypeAwareLogger;
import org.apache.dubbo.common.logger.LoggerFactory;
import org.apache.dubbo.registry.xds.snp.Snp;
import org.apache.dubbo.registry.xds.util.XdsChannel;
import org.apache.dubbo.registry.xds.util.protocol.AbstractProtocol;
import org.apache.dubbo.registry.xds.util.protocol.delta.DeltaSnp;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


public class SnpProtocol extends AbstractProtocol<List<Snp.ServiceMappingXdsResponse>, DeltaSnp> {

    private static final ErrorTypeAwareLogger logger = LoggerFactory.getErrorTypeAwareLogger(SnpProtocol.class);

    public SnpProtocol(XdsChannel xdsChannel, Node node, int pollingPoolSize, int pollingTimeout) {
        super(xdsChannel, node, pollingPoolSize, pollingTimeout);
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


}
