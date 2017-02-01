package com.redhat.brazil.consulting.fuse.exception;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Modelo padrão de exceção, que contêm um código e a mensagem.
 * 
 * @author <a href="mailto:asouza@redhat.com">Ângelo Galvão</a>
 *
 */
@XmlRootElement
public class ExceptionMessage {
	@XmlElement
	private int statusCode;
	@XmlElement
	private String message;	
	
	public ExceptionMessage() {}
	
	public ExceptionMessage(int statusCode, String message) {
		this.statusCode = statusCode;
		this.message = message;
	}
	public int getStatusCode() {
		return statusCode;
	}
	public String getMessage() {
		return message;
	}
}
