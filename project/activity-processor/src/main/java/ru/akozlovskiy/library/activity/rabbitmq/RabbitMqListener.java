package ru.akozlovskiy.library.activity.rabbitmq;

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
		System.out.println("RECEIVED FROM all-activity-queue: " + message);

		logger.info("RECEIVED FROM all-activity-queue: {}", message);

		try {
			// val userActivity = objectMapper.readValue(message, UserActivity.class);

			// System.out.println("userActivity: " + userActivity);

			// activityRepository.save(userActivity);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}