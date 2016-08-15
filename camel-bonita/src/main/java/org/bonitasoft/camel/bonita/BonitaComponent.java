package org.bonitasoft.camel.bonita;

import java.util.Map;

import org.apache.camel.CamelContext;
import org.apache.camel.Endpoint;
import org.apache.camel.impl.UriEndpointComponent;
import org.bonitasoft.camel.bonita.util.BonitaOperation;

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
        configuration.setParameters(parameters);
        configuration.setOperation(BonitaOperation.valueOf(remaining));
        setProperties(configuration, parameters);
        Endpoint endpoint = new BonitaEndpoint(uri, this, configuration);
        return endpoint;
    }
}
