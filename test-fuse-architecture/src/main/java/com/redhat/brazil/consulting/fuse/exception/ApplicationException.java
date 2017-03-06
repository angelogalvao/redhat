package com.redhat.brazil.consulting.fuse.exception;

/**
 * Exceção de negócio padrão.
 * 
 * @author <a href="mailto:asouza@redhat.com">Ângelo Galvão</a>
 *
 */
public class ApplicationException extends Exception {

	private static final long serialVersionUID = 3362215057543667109L;

	private ExceptionMessage em;

	public ApplicationException(int statusCode, String message) {
		super(message);
		em = new ExceptionMessage(statusCode, message);		
		
	}
	
	public ExceptionMessage getExceptionMessage(){
		
		return em;
	}

}
