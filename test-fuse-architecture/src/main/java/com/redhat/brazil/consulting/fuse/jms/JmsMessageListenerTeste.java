package com.redhat.brazil.consulting.fuse.jms;

import java.net.URISyntaxException;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.MessageConsumer;
import javax.jms.Queue;
import javax.jms.Session;

import org.apache.activemq.ActiveMQConnectionFactory;

public class JmsMessageListenerTeste {
	
	public static void main(String[] args) throws URISyntaxException, Exception {

		Connection connection = null;
		try {
			// Producer
			ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("admin", "admin", "tcp://localhost:61616");
			connection = connectionFactory.createConnection();
			
			Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			
			
			Queue queue = session.createQueue("teste1");
			
			
//			String payload = "Important Task";
//			Message msg = session.createTextMessage(payload);
//			MessageProducer producer = session.createProducer(queue);
//			System.out.println("Sending text '" + payload + "'");
//			producer.send(msg);

			// Consumer
			MessageConsumer consumer = session.createConsumer(queue);
			PortoConectaMessageListener listener = new PortoConectaMessageListener("Consumidor 1");
			consumer.setMessageListener(listener);
			connection.start();
			
			System.out.println("Escutando... ");

			synchronized (listener) {
				listener.wait();
			}
			
			
			session.close();
			
		} finally {
			if (connection != null) {
				connection.close();
			}
		}
	}

}