package com.s3.systems.crm.auditlog.app.brokers;

import org.springframework.beans.factory.annotation.Autowired;       
import org.springframework.jms.JmsException;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.s3.systems.crm.auditlog.app.models.AuditLogEntity;
import com.s3.systems.crm.auditlog.app.services.AuditLogService;

@Component
public class MessageBrokerService implements MessageBroker {

	private static final String MESSAGE_QUEUE = "audit-log-queue";
	@Autowired
	private AuditLogService auditLogService;

	@Autowired
	private Gson gson;

	@Autowired
	private JmsTemplate jmsTemplate;

	@Override
	public void send(AuditLogEntity<?> auditLog) throws JmsException {
		System.out.println("about to send message to queue");
		String jsonMessage = gson.toJson(auditLog);
		jmsTemplate.convertAndSend(MESSAGE_QUEUE, jsonMessage);
		System.out.println("finished sending message...");
	}

	@Override
	@JmsListener(destination = MESSAGE_QUEUE)
	public void recieve() {
		System.out.println("Message Received...");
		String recievedJsonMessage = (String) jmsTemplate.receiveAndConvert(MESSAGE_QUEUE);
		AuditLogEntity<?> auditLog = gson.fromJson(recievedJsonMessage, AuditLogEntity.class);
		auditLogService.createAuditLog(auditLog);
		System.out.println("Message logged ");

	}

}
