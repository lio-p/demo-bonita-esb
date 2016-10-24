package org.bonitasoft.camel.bonita.producer;

import java.util.Map;

import org.apache.camel.Exchange;
import org.bonitasoft.camel.bonita.BonitaConfiguration;
import org.bonitasoft.camel.bonita.BonitaEndpoint;
import org.bonitasoft.camel.bonita.api.BonitaAPI;

public class BonitaStartProducer extends BonitaProducer {

	public BonitaStartProducer(BonitaEndpoint endpoint, BonitaConfiguration configuration) {
		super(endpoint, configuration);
	}

	public void process(Exchange exchange) throws Exception {
		// Setup access type (HTTP on local host)
		String hostname = this.configuration.getHostname();
		String port = this.configuration.getPort();
		String processName = this.configuration.getProcessName();
		String username = this.configuration.getUsername();
		String password = this.configuration.getPassword();
		BonitaAPI bonitaApi = new BonitaAPI(hostname, port, username, password);
		String processDefinitionId = bonitaApi.getProcessDefinitionId(processName);
		bonitaApi.startCase(processDefinitionId, exchange.getIn().getBody(Map.class));
	}

}
