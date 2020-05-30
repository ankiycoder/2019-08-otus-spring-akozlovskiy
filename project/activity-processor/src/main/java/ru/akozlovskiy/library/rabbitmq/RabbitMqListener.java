package ru.akozlovskiy.library.rabbitmq;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class RabbitMqListener {

	private final ObjectMapper objectMapper;

	@RabbitListener(queues = "all-activity-queue")
	public void processAllMessages(String message) throws JsonProcessingException {
		System.out.println("RECEIVED FROM all-activity-queue: " + message);

		try {
			// val userActivity = objectMapper.readValue(message, UserActivity.class);

			// System.out.println("userActivity: " + userActivity);

			// activityRepository.save(userActivity);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}