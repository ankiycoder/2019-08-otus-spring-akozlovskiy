package ru.akozlovskiy.springdz15.integration;

import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.messaging.handler.annotation.Payload;

import ru.akozlovskiy.springdz15.domain.Document;

@MessagingGateway
public interface DocumentProcessorGateway {

	@Gateway(requestChannel = "documentFlow.input")
	void process(@Payload Document document);
}