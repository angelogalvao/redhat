package com.redhat.brazil.consulting.fuse.jms;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;

public class PortoJmsRoute extends RouteBuilder{

	@Override
	public void configure() throws Exception {
		
		System.out.println("Configurou");
		
//		from("activemq:queue:teste1")
//			.process(new Processor() {
//				
//				@Override
//				public void process(Exchange exchange) throws Exception {
//					System.out.println("recebeu: " + exchange.getIn().getBody());
//				}
//			});
		
//		from("activemq:queue:testeJava")
//		.bean(PortoProcessor.class, "processar");
		
		
	}

}
