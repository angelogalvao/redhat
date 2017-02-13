package com.redhat.brazil.consulting.fuse.messaging.jms;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.DeliveryMode;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.ScheduledMessage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class JmsClientTest {
	
	private Connection connection;
	private Session session;
	
	@Before
	public void abrir() throws JMSException{
		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("admin", "admin", "tcp://localhost:61616");
		connection = connectionFactory.createConnection();
		
		session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
	}
	
	@After
	public void fechar() throws JMSException{
		session.close();
		connection.close();
	}
	
	
	public void testeExpiracao() throws JMSException{
		Queue queue = session.createQueue("testeExpiracao");
		MessageProducer producer = session.createProducer(queue);
		
		Message msg = session.createTextMessage("Teste de expiracao 6");
		
		producer.setDeliveryMode(DeliveryMode.PERSISTENT);
		producer.setTimeToLive(10 * 1000);
		producer.send(msg);
		
		System.out.println("Enviou");
	}
	
	
	public void testeMensagemAgendada() throws JMSException{
		Queue queue = session.createQueue("testeMensagemAgendada");
		MessageProducer producer = session.createProducer(queue);
		
		Message msg = session.createTextMessage("Teste Mensagem Agendada 9");
		long time = 60 * 1000;
		msg.setLongProperty(ScheduledMessage.AMQ_SCHEDULED_DELAY, time);
				
		producer.send(msg);
		
		System.out.println("Enviou ");
	}
	
	@Test
	public void testeMensagemAgendadaConversao() throws JMSException{
		Queue queue = session.createQueue("testeMensagemAgendadaConversaoIn");
		MessageProducer producer = session.createProducer(queue);
		
		Message msg = session.createTextMessage("Teste Mensagem Agendada Conversao");
				
		producer.send(msg);
		
		System.out.println("Enviou ");
	}
	

	
	public static void main(String[] args) throws Exception {
		 
		Connection connection = null;
		try {
			// Producer
			
			
			
			
			
			
			
		} finally {
			if (connection != null) {
				connection.close();
			}
		}
	
	
	}

}
