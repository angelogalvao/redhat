package com.redhat.brazil.consulting.fuse.jms;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.redhat.brazil.consulting.fuse.model.Pessoa;

public class PortoConectaMessageListener implements MessageListener {
	private String consumerName;

	public PortoConectaMessageListener(String consumerName) {
		this.consumerName = consumerName;
	}

	public void onMessage(Message message) {
		TextMessage textMessage = (TextMessage) message;
		try {
			
			String messageText = textMessage.getText();
			
			ObjectMapper mapper = new ObjectMapper();
			
			ObjectReader pessoaReader = mapper.readerFor(Pessoa.class);
			Pessoa pessoa = pessoaReader.readValue(messageText);
			
			System.out.println(consumerName + " recebeu "	+ pessoa.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}