package com.app.jms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.app.model.Email;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class Sender {

	@Autowired
	private JmsTemplate jmsTemplate;

	public void sendEmailMessage(String destination, Email email) {
		log.info("Sending message to {} destination with text {}", destination, email);
		jmsTemplate.convertAndSend(destination, email);
	}
	
	public void sendTextMessage(String destination, String message) {
		log.info("Sending message to {} destination with text {}", destination, message);
        jmsTemplate.send(destination, s -> s.createTextMessage(message));
    }
}
