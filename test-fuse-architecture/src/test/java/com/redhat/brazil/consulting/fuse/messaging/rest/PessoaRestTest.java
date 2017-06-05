package com.redhat.brazil.consulting.fuse.messaging.rest;

import org.apache.camel.test.blueprint.CamelBlueprintTestSupport;
import org.junit.Test;

public class PessoaRestTest extends CamelBlueprintTestSupport {

	@Override
	protected String getBlueprintDescriptor() {
		return "OSGI-INF/blueprint/blueprint.xml";
	}

	@Test
	public void testPessoaAPI(){
		
		String body = "{\"nome\":\"Luke Skywalker\",\"nascimento\":417810411633}";
		
		template.sendBody("cxfrs://bean://pessoa-api?bindingStyle=SimpleConsumer", body);
	}
	
	
}
