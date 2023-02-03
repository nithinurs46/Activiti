package com.app.jms;

import javax.jms.JMSException;
import javax.jms.TextMessage;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import com.app.model.Email;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class Receiver {
	@JmsListener(destination = "queue-mailbox", containerFactory = "jmsListenerContainerFactory")
	public void receiveEmailMessage(Email email) {
		log.info("JMS listener received message: {}", " <" + email + ">");
	}
	
	@JmsListener(destination = "queue-1", containerFactory = "jmsListenerContainerFactory")
	public void receiveTextMessage(TextMessage message) {
		try {
			log.info("JMS listener received text message: {}", message.getText());
		} catch (JMSException e) {
			log.error(e.getMessage());
		}
	}
}
