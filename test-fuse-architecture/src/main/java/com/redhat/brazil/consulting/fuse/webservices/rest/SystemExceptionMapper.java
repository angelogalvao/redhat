package com.redhat.brazil.consulting.fuse.webservices.rest;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.apache.camel.Exchange;

/**
 * Mapeia exceçao de sistema unchecked.
 * @author <a href="mailto:asouza@redhat.com">Ângelo Galvão</a>
 *
 */
@Provider
public class SystemExceptionMapper implements ExceptionMapper<RuntimeException>{

	@Override
	public Response toResponse(RuntimeException exception) {
		return Response.status(Status.INTERNAL_SERVER_ERROR)
				.type(MediaType.TEXT_PLAIN)
				.entity(exception.getMessage())				
				.build();
	}
	
	
	public Response toResponseCamel(Exchange exchange) {
		
		Exception exception = exchange.getProperty(Exchange.EXCEPTION_CAUGHT, Exception.class);
		
		return Response.status(Status.INTERNAL_SERVER_ERROR)
				.type(MediaType.TEXT_PLAIN)
				.entity(exception.getMessage())				
				.build();
	}

}
