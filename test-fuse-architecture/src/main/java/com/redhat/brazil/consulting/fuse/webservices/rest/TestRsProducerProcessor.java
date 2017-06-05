package com.redhat.brazil.consulting.fuse.webservices.rest;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestRsProducerProcessor implements Processor{
	
	private Logger log = LoggerFactory.getLogger(TestRsProducerProcessor.class);
	
	private String xyz;
	
	public void setXyz(String xyz) {
		this.xyz = xyz;
	}
	
	public void process(Exchange exchange){
		
		exchange.copy(true);
		
		log.info("Execução realizada com sucesso");
		
	}

}
