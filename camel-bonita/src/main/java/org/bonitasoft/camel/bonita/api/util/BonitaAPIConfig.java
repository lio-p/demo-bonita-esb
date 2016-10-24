package org.bonitasoft.camel.bonita.api.util;

public class BonitaAPIConfig {
	
	private String hostname;
	private String port;
	private String username;
	private String password;
	
	
	public BonitaAPIConfig(String hostname, String port, String username, String password) {
		super();
		this.hostname = hostname;
		this.port = port;
		this.username = username;
		this.password = password;
	}
	
	public String getBaseBonitaURI() {
		return "http://" + hostname + ":" + port + "/bonita";
	}
	
	
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
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	

}
