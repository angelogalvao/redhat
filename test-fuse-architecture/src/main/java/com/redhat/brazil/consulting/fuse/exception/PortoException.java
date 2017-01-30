package com.redhat.brazil.consulting.fuse.exception;

/**
 * Exceção padrao da Porto.
 * @author asouza
 *
 */

public class PortoException extends Exception {

	private static final long serialVersionUID = 3362215057543667109L;

	private ExceptionMessage em;

	public PortoException(int statusCode, String message) {
		super(message);
		em = new ExceptionMessage(statusCode, message);		
		
	}
	
	public ExceptionMessage getExceptionMessage(){
		
		return em;
	}

}
