package org.bonitasoft.camel.bonita.producer;

import org.apache.camel.impl.DefaultProducer;
import org.bonitasoft.camel.bonita.BonitaConfiguration;
import org.bonitasoft.camel.bonita.BonitaEndpoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The bonita producer.
 */
public abstract class BonitaProducer extends DefaultProducer {
    private static final Logger LOG = LoggerFactory.getLogger(BonitaProducer.class);

    protected BonitaEndpoint endpoint;
    protected BonitaConfiguration configuration;

    public BonitaProducer(BonitaEndpoint endpoint, BonitaConfiguration configuration) {
        super(endpoint);
        this.endpoint = endpoint;
        this.configuration = configuration;
    }
}
