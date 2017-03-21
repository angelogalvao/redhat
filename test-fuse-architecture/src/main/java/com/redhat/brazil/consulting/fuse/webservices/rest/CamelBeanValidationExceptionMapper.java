package com.redhat.brazil.consulting.fuse.webservices.rest;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.apache.camel.component.bean.validator.BeanValidationException;

/**
 * JAX-RS mapping of BeanValidationException.
 * @author <a href="mailto:asouza@redhat.com">Ângelo Galvão</a>
 *
 */
@Provider
public class CamelBeanValidationExceptionMapper implements ExceptionMapper<BeanValidationException> {

	@Override
	public Response toResponse(BeanValidationException exception) {
		
		String responseMessage = "";
		Set<ConstraintViolation<Object>> constraintViolations = exception.getConstraintViolations();
		
		for (ConstraintViolation<Object> constraintViolation : constraintViolations) {
			String message = constraintViolation.getMessage();
			
			responseMessage += message + "\n";
		}
				
		return Response.status(Status.BAD_REQUEST)
				.type(MediaType.APPLICATION_JSON)
				.entity(responseMessage)				
				.build();
	}

}
