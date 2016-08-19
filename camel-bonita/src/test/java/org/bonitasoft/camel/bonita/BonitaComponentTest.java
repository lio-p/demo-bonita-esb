package org.bonitasoft.camel.bonita;

import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import org.apache.camel.EndpointInject;
import org.apache.camel.Produce;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.junit4.CamelTestSupport;
import org.bonitasoft.engine.bpm.contract.FileInputValue;
import org.junit.Test;

public class BonitaComponentTest extends CamelTestSupport {

	@EndpointInject(uri = "mock:result")
	protected MockEndpoint resultEndpoint;

	@Produce(uri = "direct:start")
	protected ProducerTemplate template;


	@Test
	public void testStartProcess() throws Exception {
		String processName = "testProcess";
		Map<String, Serializable> variables = new HashMap<>();
		Path path = Paths.get("/Users/lionel/Bonita/Adoption/demo/esb_integration/Claims-letter.docx");
		byte[] data = Files.readAllBytes(path);
		FileInputValue fileInput = new FileInputValue("Claims-letter.docx", data);
		
		HashMap<String, Serializable> claim = new HashMap<>();
		claim.put("number", "1");
		claim.put("needReview", true);
		claim.put("customerId", "1");
		
		variables.put("letter", fileInput);
		variables.put("claimInput", claim);
		
		//resultEndpoint.expectedBodyReceived(variables);
	
		template.sendBodyAndHeader(variables, "processName", processName);
	
		resultEndpoint.assertIsSatisfied();
	}

	

	@Override
	protected RouteBuilder createRouteBuilder() {
		return new RouteBuilder() {
				public void configure() {
					from("direct:start").to("bonita:startCase?hostname=localhost&processName=testProcess").to("mock:result");
				}
		};
	}
	
	
}
