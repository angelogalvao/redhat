package com.redhat.brazil.consulting.fuse.messaging.jms;

import org.apache.activemq.command.ActiveMQQueue;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;

import com.redhat.brazil.consulting.fuse.exception.ApplicationException;

public class MessageExceptionHandler implements Processor {

	@Override
	public void process(Exchange exchange) throws Exception {
		
		Exception cause = exchange.getProperty(Exchange.EXCEPTION_CAUGHT, Exception.class);

		String originalDestination = ((ActiveMQQueue) exchange.getIn().getHeader("JMSDestination")).getQualifiedName();

		if( cause instanceof ApplicationException ){
			ApplicationException applicationException = (ApplicationException) cause;
			
			exchange.getIn().setHeader("StatusCode", applicationException.getExceptionMessage().getStatusCode());
			exchange.getIn().setHeader("Message", applicationException.getExceptionMessage().getMessage());
			exchange.getIn().setHeader("ExceptionType", cause.getClass().getName());
		} 
		
		exchange.getIn().setHeader("Error", cause.getMessage());		
		exchange.getIn().setHeader("OriginalDestination", originalDestination);

	}

}
