package com.redhat.brazil.consulting.fuse.webservices.rest;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.apache.camel.Exchange;

import com.redhat.brazil.consulting.fuse.exception.ApplicationException;

/**
 * JAX-RS mapping of Application Exception.
 * @author <a href="mailto:asouza@redhat.com">Ângelo Galvão</a>
 *
 */
@Provider
public class ApplicationExceptionMapper implements ExceptionMapper<ApplicationException>{

	@Override
	public Response toResponse(ApplicationException exception) {

		return Response.status(Status.BAD_REQUEST)
				.type(MediaType.APPLICATION_JSON)
				.entity(exception.getExceptionMessage())				
				.build();
	}
	
	public Response toResponseCamel(Exchange exchange) {
		
		ApplicationException exception = exchange.getProperty(Exchange.EXCEPTION_CAUGHT, ApplicationException.class);

		return toResponse(exception);
	}

}
	