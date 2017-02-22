package com.redhat.consulting;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public class DynamicRoutesProcessor implements Processor {
	

	private String test;
	
	public void setTest(String test) {
		this.test = test;
		
		
	}
	

	@Override
	public void process(Exchange exchange) throws Exception {
		
		System.out.println("aaaaaaaaaaaaa " + test);
		
		Bridges bridges = (Bridges) exchange.getIn().getBody();
		
		
		exchange.getContext().addRoutes(new BridgeRoute(bridges));
		
	}

}
