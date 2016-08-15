package org.bonitasoft.camel.bonita.consumer;

import java.util.Date;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.impl.ScheduledPollConsumer;
import org.bonitasoft.camel.bonita.BonitaConfiguration;
import org.bonitasoft.camel.bonita.BonitaEndpoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The bonita consumer.
 */
public class BonitaConsumer extends ScheduledPollConsumer {
   
    protected static final transient Logger LOG = LoggerFactory.getLogger(BonitaConsumer.class);

	
	protected BonitaEndpoint endpoint;
    protected BonitaConfiguration configuration;

    public BonitaConsumer(BonitaEndpoint endpoint, Processor processor, BonitaConfiguration configuration) {
        super(endpoint, processor);
        this.endpoint = endpoint;
        this.configuration = configuration;
    }

    @Override
    protected int poll() throws Exception {
        Exchange exchange = endpoint.createExchange();

        // create a message body
        Date now = new Date();
        exchange.getIn().setBody("Hello World! The time is " + now);

        try {
            // send message to next processor in the route
            getProcessor().process(exchange);
            return 1; // number of messages polled
        } finally {
            // log exception if an exception occurred and was not handled
            if (exchange.getException() != null) {
                getExceptionHandler().handleException("Error processing exchange", exchange, exchange.getException());
            }
        }
    }
}
