package org.bonitasoft.camel.bonita.api;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.ClientRequestContext;
import javax.ws.rs.client.ClientRequestFilter;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MultivaluedHashMap;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.NewCookie;
import javax.ws.rs.core.Response;

import org.bonitasoft.camel.bonita.api.util.BonitaAPIConfig;
import org.glassfish.jersey.client.ClientConfig;

public class BonitaAuthFilter implements ClientRequestFilter { 
	
	private BonitaAPIConfig bonitaApiConfig;	
	
	public BonitaAuthFilter(BonitaAPIConfig bonitaApiConfig) {
		this.bonitaApiConfig = bonitaApiConfig;
		
	}
	
	@Override
    public void filter(ClientRequestContext requestContext) throws IOException {
        if (requestContext.getCookies().get("JSESSIONID") == null) {
        	ClientConfig clientConfig = new ClientConfig();
            ClientBuilder clientBuilder = ClientBuilder.newBuilder().withConfig(clientConfig);
            Client client = clientBuilder.build();
            WebTarget webTarget = client.target(bonitaApiConfig.getBaseBonitaURI()).path("loginservice");
            MultivaluedMap<String, String> form = new MultivaluedHashMap<String, String>();
            form.add("username", bonitaApiConfig.getUsername());
            form.add("password", bonitaApiConfig.getPassword());
            form.add("redirect", "false");
            Response response = webTarget.request().accept("application/x-www-form-urlencoded").post(Entity.form(form));
            Map<String, NewCookie> cr = response.getCookies();
            ArrayList<Object> cookies = new ArrayList<>();
            for (NewCookie cookie : cr.values()) {
            	cookies.add(cookie.toCookie());
            }
            requestContext.getHeaders().put("Cookie", cookies);
        }
           
    }



}
