package org.bonitasoft.camel.bonita;

import java.util.Map;

import org.apache.camel.spi.Metadata;
import org.apache.camel.spi.UriParam;
import org.apache.camel.spi.UriPath;
import org.bonitasoft.camel.bonita.util.BonitaOperation;

public class BonitaConfiguration {
	
	// Specific Bonita operation for the component
    @UriPath @Metadata(required = "true")
    private BonitaOperation operation;
    
    @UriParam(defaultValue="localhost")
    private String hostname = "localhost";
    
    @UriParam(defaultValue="8080")
	private String port = "8080";
    
    @UriParam
    private String processName;
    
    
    public String getHostname() {
		return hostname;
	}

	public void setHostname(String hostname) {
		this.hostname = hostname;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public String getProcessName() {
		return processName;
	}

	public void setProcessName(String processName) {
		this.processName = processName;
	}


    public BonitaOperation getOperation() {
        return operation;
    }

    public void setOperation(BonitaOperation operation) {
        this.operation = operation;
    }



	public void setParameters(Map<String, Object> parameters) {
		if (parameters.get("hostname") != null) this.hostname = (String) parameters.get("hostname");
		if (parameters.get("port") != null) this.port = (String) parameters.get("port");
		if (parameters.get("processName") != null) this.processName = (String) parameters.get("processName");

	}

}
