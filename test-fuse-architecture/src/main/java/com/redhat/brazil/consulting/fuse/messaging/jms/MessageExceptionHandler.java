package com.redhat.brazil.consulting.fuse.messaging.jms;

import java.util.Map;

import org.apache.activemq.command.ActiveMQQueue;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public class MessageExceptionHandler implements Processor {

	@Override
	public void process(Exchange exchange) throws Exception {

		Map<String, Object> headers = (Map<String, Object>) exchange.getIn().getHeaders();
		
		for (String key : headers.keySet()) {
			System.out.println(key + ":" + headers.get(key));
		}
		
		Exception cause = exchange.getProperty(Exchange.EXCEPTION_CAUGHT, Exception.class);

		String originalDestination = ((ActiveMQQueue) exchange.getIn().getHeader("JMSDestination")).getQualifiedName();

		exchange.getIn().setHeader("Error", cause.getMessage());
		exchange.getIn().setHeader("OriginalDestination", originalDestination);

	}

}
