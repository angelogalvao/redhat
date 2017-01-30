package com.redhat.brazil.consulting.fuse.webservices.rest;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * Mapeia exceçao de sistema unchecked.
 * @author asouza
 *
 */
@Provider
public class PortoConectaSystemExceptionMapper implements ExceptionMapper<RuntimeException>{

	@Override
	public Response toResponse(RuntimeException exception) {
		return Response.status(Status.INTERNAL_SERVER_ERROR)
				.type(MediaType.TEXT_PLAIN)
				.entity(exception.getMessage())				
				.build();
	}

}
