package ru.akozlovskiy.userstat.rabbitmq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;
import ru.akozlovskiy.userstat.domain.UserRequest;
import ru.akozlovskiy.userstat.repositories.UserRequestRepository;

@RequiredArgsConstructor
@Service
public class RabbitMqListener {

	private static Logger logger = LoggerFactory.getLogger(RabbitMqListener.class);

	private final ObjectMapper objectMapper;
	
	private final UserRequestRepository userRequestRepository;

	@RabbitListener(queues = "user-request-queue")
	public void processAllMessages(String message) throws JsonProcessingException {

		logger.info("RECEIVED FROM user-request-queue: {}", message);

		try {
			UserRequest userActivity = objectMapper.readValue(message, UserRequest.class);
			userRequestRepository.save(userActivity);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}