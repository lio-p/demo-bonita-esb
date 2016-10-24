//
//  Licensed to the Apache Software Foundation (ASF) under one or more
//  contributor license agreements.  See the NOTICE file distributed with
//  this work for additional information regarding copyright ownership.
//  The ASF licenses this file to You under the Apache License, Version 2.0
//  (the "License"); you may not use this file except in compliance with
//  the License.  You may obtain a copy of the License at
//
//  http://www.apache.org/licenses/LICENSE-2.0
//
//  Unless required by applicable law or agreed to in writing, software
//  distributed under the License is distributed on an "AS IS" BASIS,
//  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
//  See the License for the specific language governing permissions and
//  limitations under the License.

package org.bonitasoft.camel.bonita.api;

import static javax.ws.rs.client.Entity.entity;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;

import org.bonitasoft.camel.bonita.api.model.CaseCreationResponse;
import org.bonitasoft.camel.bonita.api.model.ProcessDefinitionResponse;
import org.bonitasoft.camel.bonita.api.util.BonitaAPIConfig;
import org.bonitasoft.camel.bonita.exception.BonitaException;
import org.glassfish.jersey.client.ClientConfig;

import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;


public class BonitaAPI {

	private BonitaAPIConfig bonitaApiConfig;
	private WebTarget webTarget;

	public BonitaAPI(String hostname, String port, String username, String password) {
		super();
		bonitaApiConfig = new BonitaAPIConfig(hostname, port, username, password);
		init();
	}
	
	private void init() {
		ClientConfig clientConfig = new ClientConfig();
        clientConfig.register(JacksonJsonProvider.class);
        ClientBuilder clientBuilder = ClientBuilder.newBuilder().withConfig(clientConfig);
        Client client = clientBuilder.build();
        client.register(new BonitaAuthFilter(bonitaApiConfig));
        webTarget = client.target(bonitaApiConfig.getBaseBonitaURI()).path("/API/bpm");
	}
	
	private WebTarget getBaseResource() {
		return webTarget;
	}
 	
	public String getProcessDefinitionId(String processName) throws BonitaException {
		try {
			WebTarget resource = getBaseResource().path("process").queryParam("s", processName);
			List<ProcessDefinitionResponse> listProcess = resource.request().accept(MediaType.APPLICATION_JSON).get(new GenericType<List<ProcessDefinitionResponse>> () {});
			if (listProcess.size() > 0) {
				return listProcess.get(0).getId();
			} else {
				throw new BonitaException("The process with name " + processName + " does not exist");
			}		
		}
		catch (Exception e) {
			throw new BonitaException(e.getMessage());
		}
	}
	
	public CaseCreationResponse startCase(String processDefinitionId, Map<String, Serializable> inputs) {
		WebTarget resource = getBaseResource().path("process/{processId}/instantiation").resolveTemplate("processId", processDefinitionId);
		return resource.request().accept(MediaType.APPLICATION_JSON).post(entity(inputs,MediaType.APPLICATION_JSON), CaseCreationResponse.class);
	}
	
	
	
	
}
