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
import io.envoyproxy.envoy.config.route.v3.Route;
import io.envoyproxy.envoy.config.route.v3.RouteAction;
import io.envoyproxy.envoy.config.route.v3.RouteConfiguration;
import io.envoyproxy.envoy.service.discovery.v3.DiscoveryResponse;
import istio.extensions.v1alpha1.ServiceNameMappingOuterClass;
import org.apache.dubbo.common.logger.ErrorTypeAwareLogger;
import org.apache.dubbo.common.logger.LoggerFactory;
import org.apache.dubbo.registry.xds.util.XdsChannel;
import org.apache.dubbo.registry.xds.util.protocol.AbstractProtocol;
import org.apache.dubbo.registry.xds.util.protocol.delta.DeltaRoute;
import org.apache.dubbo.registry.xds.util.protocol.delta.DeltaSnp;
import org.apache.dubbo.registry.xds.util.protocol.message.RouteResult;

import java.util.*;
import java.util.stream.Collectors;

import static org.apache.dubbo.common.constants.LoggerCodeConstants.REGISTRY_ERROR_RESPONSE_XDS;

public class SnpProtocol extends AbstractProtocol<List<ServiceNameMappingOuterClass.ServiceNameMapping>, DeltaSnp> {

    private static final ErrorTypeAwareLogger logger = LoggerFactory.getErrorTypeAwareLogger(SnpProtocol.class);

    public SnpProtocol(XdsChannel xdsChannel, Node node, int pollingPoolSize, int pollingTimeout) {
        super(xdsChannel, node, pollingPoolSize, pollingTimeout);
    }

    @Override
    public String getTypeUrl() {
        return "dubbo.networking.v1alpha1.v1.servicenamemapping";
    }

    @Override
    protected List<ServiceNameMappingOuterClass.ServiceNameMapping> decodeDiscoveryResponse(DiscoveryResponse response) {
        if (getTypeUrl().equals(response.getTypeUrl())) {
            List<ServiceNameMappingOuterClass.ServiceNameMapping> map = response.getResourcesList().stream()
                .map(SnpProtocol::unpackSnpConfiguration)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
            return map;
        }
        return null;
    }

    private static ServiceNameMappingOuterClass.ServiceNameMapping unpackSnpConfiguration(Any any) {
        try {
            return any.unpack(ServiceNameMappingOuterClass.ServiceNameMapping.class);
        } catch (InvalidProtocolBufferException e) {
            logger.error(REGISTRY_ERROR_RESPONSE_XDS, "", "", "Error occur when decode xDS response.", e);
            return null;
        }
    }


}
