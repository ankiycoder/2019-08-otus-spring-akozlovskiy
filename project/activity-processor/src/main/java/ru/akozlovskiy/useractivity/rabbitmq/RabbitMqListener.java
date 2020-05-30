package ru.akozlovskiy.useractivity.rabbitmq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class RabbitMqListener {

	private static Logger logger = LoggerFactory.getLogger(RabbitMqListener.class);

	private final ObjectMapper objectMapper;

	@RabbitListener(queues = "all-activity-queue")
	public void processAllMessages(String message) throws JsonProcessingException {

		logger.info("RECEIVED FROM all-activity-queue: {}", message);

		try {
			// activityRepository.save(userActivity);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}