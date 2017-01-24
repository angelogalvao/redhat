package br.com.portoconecta.fuse.piloto.exception;

import javax.xml.ws.WebFault;

/**
 * Exceção padrao da Porto.
 * @author asouza
 *
 */
@WebFault(name="ExceptionMessage",
		  targetNamespace="http://www.portoconecta.com.br/exceptions")
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
