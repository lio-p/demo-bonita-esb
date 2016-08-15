package org.bonitasoft.camel.bonita;

import org.apache.camel.Consumer;
import org.apache.camel.Processor;
import org.apache.camel.Producer;
import org.apache.camel.impl.DefaultEndpoint;
import org.apache.camel.spi.UriEndpoint;
import org.apache.camel.spi.UriParam;
import org.bonitasoft.camel.bonita.consumer.BonitaConsumer;
import org.bonitasoft.camel.bonita.producer.BonitaStartProducer;
import org.bonitasoft.camel.bonita.util.BonitaOperation;
import org.bonitasoft.engine.exception.BonitaException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Represents a bonita endpoint.
 */
@UriEndpoint(scheme = "bonita", title = "bonita", syntax="bonita:operation", consumerClass = BonitaConsumer.class, label = "bonita")
public class BonitaEndpoint extends DefaultEndpoint {

    private static final transient Logger LOG = LoggerFactory.getLogger(BonitaEndpoint.class);
	
    @UriParam
    private BonitaConfiguration configuration;

    public BonitaEndpoint() {
    }
    
    public BonitaEndpoint(String uri, BonitaComponent component, BonitaConfiguration configuration) {
        super(uri, component);
        this.configuration = configuration;
    }

    public BonitaEndpoint(String endpointUri) {
        super(endpointUri);
    }

    public Producer createProducer() throws Exception {
        if (configuration.getOperation() == BonitaOperation.startCase) {
	        return new BonitaStartProducer(this, configuration);
        } else {
        	throw new BonitaException("Operation specified is not supported.");
        }
    }

    public Consumer createConsumer(Processor processor) throws Exception {
        return new BonitaConsumer(this, processor, configuration);
    }

    public boolean isSingleton() {
        return true;
    }


}
