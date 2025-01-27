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
package org.apache.dubbo.registry.xds.util.protocol.delta;

import istio.extensions.v1alpha1.ServiceNameMappingOuterClass;
import org.apache.dubbo.common.utils.CollectionUtils;
import org.apache.dubbo.registry.xds.util.protocol.DeltaResource;
import org.apache.dubbo.registry.xds.util.protocol.message.RouteResult;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class DeltaSnp implements DeltaResource<List<ServiceNameMappingOuterClass.ServiceNameMapping>> {
    private final Map<String, Map<String, Set<String>>> data = new ConcurrentHashMap<>();

    public void addResource(String resourceName, Map<String, Set<String>> route) {
        data.put(resourceName, route);
    }

    public void removeResource(Collection<String> resourceName) {
        if (CollectionUtils.isNotEmpty(resourceName)) {
            resourceName.forEach(data::remove);
        }
    }

    @Override
    public List<ServiceNameMappingOuterClass.ServiceNameMapping> getResource() {
        Map<String, Set<String>> result = new ConcurrentHashMap<>();
        data.values().forEach(result::putAll);
        return null;
    }
}
