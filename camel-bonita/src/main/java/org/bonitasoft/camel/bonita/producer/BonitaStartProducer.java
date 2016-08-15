package org.bonitasoft.camel.bonita.producer;

import java.util.HashMap;
import java.util.Map;

import org.apache.camel.Exchange;
import org.bonitasoft.camel.bonita.BonitaConfiguration;
import org.bonitasoft.camel.bonita.BonitaEndpoint;
import org.bonitasoft.engine.api.ApiAccessType;
import org.bonitasoft.engine.api.IdentityAPI;
import org.bonitasoft.engine.api.LoginAPI;
import org.bonitasoft.engine.api.ProcessAPI;
import org.bonitasoft.engine.api.TenantAPIAccessor;
import org.bonitasoft.engine.bpm.process.ProcessInstance;
import org.bonitasoft.engine.session.APISession;
import org.bonitasoft.engine.util.APITypeManager;

public class BonitaStartProducer extends BonitaProducer {
	
	public BonitaStartProducer(BonitaEndpoint endpoint, BonitaConfiguration configuration) {
        super(endpoint, configuration);
    }
	
	public void process(Exchange exchange) throws Exception {
    	//String action = this.endpoint.getAction();
        // Setup access type (HTTP on local host)
	    Map<String, String> parameters = new HashMap<String, String>();
	    String hostname = this.configuration.getHostname();
	    String port = this.configuration.getPort();
	    String processName = this.configuration.getProcessName();
	    parameters.put("server.url", "http://"+hostname+":"+port);
	    parameters.put("application.name", "bonita");
	    APITypeManager.setAPITypeAndParams(ApiAccessType.HTTP, parameters);
	
	    // Authenticate and obtain API session
	    LoginAPI loginAPI = TenantAPIAccessor.getLoginAPI();
	    APISession session = loginAPI.login("install","install");
	    IdentityAPI identityApi = TenantAPIAccessor.getIdentityAPI(session);
	    ProcessAPI processApi = TenantAPIAccessor.getProcessAPI(session);
	    long processDefinitionId = processApi.getLatestProcessDefinitionId(processName);   
	    ProcessInstance processInstance = processApi.startProcessWithInputs(processDefinitionId, exchange.getIn().getBody(Map.class));
    }

}
