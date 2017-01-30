package com.redhat.brazil.consulting.fuse.webservices.rest;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.redhat.brazil.consulting.fuse.exception.PortoException;

/**
 * Mapeia as exceçoes de negócio da porto.
 * @author asouza
 *
 */
@Provider
public class PortoConectaExceptionMapper implements ExceptionMapper<PortoException>{

	@Override
	public Response toResponse(PortoException exception) {

		return Response.status(Status.BAD_REQUEST)
				.type(MediaType.APPLICATION_JSON)
				.entity(exception.getExceptionMessage())				
				.build();
	}

}
	