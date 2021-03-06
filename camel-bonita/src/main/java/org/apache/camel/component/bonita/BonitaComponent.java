/**
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.camel.component.bonita;

import java.util.Map;

import org.apache.camel.CamelContext;
import org.apache.camel.Endpoint;
import org.apache.camel.component.bonita.util.BonitaOperation;
import org.apache.camel.impl.UriEndpointComponent;

/**
 * Represents the component that manages {@link BonitaEndpoint}.
 */
public class BonitaComponent extends UriEndpointComponent {
    
    public BonitaComponent() {
        super(BonitaEndpoint.class);
    }

    public BonitaComponent(CamelContext context) {
        super(context, BonitaEndpoint.class);
    }

    protected Endpoint createEndpoint(String uri, String remaining, Map<String, Object> parameters) throws Exception {
        BonitaConfiguration configuration = new BonitaConfiguration();
//        configuration.setParameters(parameters);
        setProperties(configuration, parameters);
        configuration.setOperation(BonitaOperation.valueOf(remaining));
        Endpoint endpoint = new BonitaEndpoint(uri, this, configuration);
        return endpoint;
    }
}
