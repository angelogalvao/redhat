package br.com.portoconecta.fuse.piloto.exception;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

/**
 * Modelo de exceção.
 * 
 * @author asouza
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType
public class ExceptionMessage {
	private int statusCode;
	private String message;
	
	
	public ExceptionMessage(int statusCode, String message) {
		super();
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
