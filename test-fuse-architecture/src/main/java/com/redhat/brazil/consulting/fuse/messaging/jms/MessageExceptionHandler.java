package com.redhat.brazil.consulting.fuse.messaging.jms;

import javax.validation.ValidationException;

import org.apache.activemq.command.ActiveMQQueue;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.redhat.brazil.consulting.fuse.exception.ApplicationException;

/**
 * Handler de exceções de mensagens trafegadas no broker.
 * 
 * @author <a href="mailto:asouza@redhat.com">Ângelo Galvão</a>
 *
 */
public class MessageExceptionHandler implements Processor {

	@Override
	public void process(Exchange exchange) throws Exception {
		
		Exception cause = exchange.getProperty(Exchange.EXCEPTION_CAUGHT, Exception.class);

		String originalDestination = ((ActiveMQQueue) exchange.getIn().getHeader("JMSDestination")).getQualifiedName();
		
		Logger logger = LoggerFactory.getLogger("AMQ."+originalDestination);
		Object body = exchange.getIn().getBody();
		

		if( cause instanceof ApplicationException ){
			ApplicationException applicationException = (ApplicationException) cause;
			
			exchange.getIn().setHeader("StatusCode", applicationException.getExceptionMessage().getStatusCode());
			exchange.getIn().setHeader("Message", applicationException.getExceptionMessage().getMessage());
			exchange.getIn().setHeader("ExceptionType", cause.getClass().getName());
			
			logger.debug("JMS "+ body, cause);
		} else if(cause instanceof ValidationException ) {
			// TODO extract the message
		} else {
			
			logger.error("JMS "+ body, cause);
		}
		
		exchange.getIn().setHeader("Error", cause.getMessage());		
		exchange.getIn().setHeader("OriginalDestination", originalDestination);

	}

}
