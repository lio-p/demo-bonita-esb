package org.bonitasoft.esb.bean;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import org.apache.camel.BeanInject;
import org.apache.camel.Produce;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.test.junit4.CamelTestSupport;
import org.junit.Test;

public class WordDocumentExtractorBeanTest extends CamelTestSupport {
	
	WordDocumentExtractorBean wordDocumentExtractorBean;
	
	@Produce(uri = "direct:start")
	protected ProducerTemplate template;
	
	
	
	@Test
	public void extractCustomerReference() throws IOException {
		WordDocumentExtractorBean wordDocumentExtractorBean = new WordDocumentExtractorBean();
		File initialFile = new File("/Users/lionel/Bonita/Adoption/demo/esb_integration/Claims-letter.docx");
		InputStream targetStream = new FileInputStream(initialFile);		
		//wordDocumentExtractorBean.processClaimsLetter(targetStream);
	}
	
	@Override
	protected RouteBuilder createRouteBuilder() {
		return new RouteBuilder() {
				public void configure() {
					wordDocumentExtractorBean = new WordDocumentExtractorBean();
					from("file:/Users/lionel/Bonita/Adoption/demo/esb_integration/Claims-letter.docx").bean(wordDocumentExtractorBean, "processClaimsLetter").to("mock:result");
				}
		};
	}
	
	

}
