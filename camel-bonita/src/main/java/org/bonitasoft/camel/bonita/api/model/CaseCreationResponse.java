package org.bonitasoft.camel.bonita.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CaseCreationResponse {
	
	@JsonProperty("caseId")
	private String caseId;

	public String getCaseId() {
		return caseId;
	}

	public void setCaseId(String caseId) {
		this.caseId = caseId;
	}
	
	

}
