package com.redhat.consulting;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Entidade Bridge que representa a ponte entre uma origem e um destino.
 * en: Bridge entity.
 * 
 * @author <a href="mailto:asouza@redhat.com">Ângelo Galvão</a>
 *
 */
@XmlRootElement
public class Bridge implements Serializable {

	private static final long serialVersionUID = -5273935601750135467L;

	private String from;
	private String to;
	private Long messagesPerSecond;
	
	public String getId(){
		return from + "-" + to + "-bridge";
	}
	
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public String getTo() {
		return to;
	}
	public void setTo(String to) {
		this.to = to;
	}
	public Long getMessagesPerSecond() {
		return messagesPerSecond;
	}
	public void setMessagesPerSecond(Long messagesPerSecond) {
		this.messagesPerSecond = messagesPerSecond;
	} 
}
