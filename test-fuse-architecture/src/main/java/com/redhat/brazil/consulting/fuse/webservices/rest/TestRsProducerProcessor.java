package com.redhat.brazil.consulting.fuse.webservices.rest;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public class TestRsProducerProcessor implements Processor{
	
	public void process(Exchange exchange){

		TestRsProducerResponse body =  (TestRsProducerResponse) exchange.getIn().getBody();
        System.out.println("Servidor remoto recebeu " + body.getArgs().get("nome"));
		
	}

}
