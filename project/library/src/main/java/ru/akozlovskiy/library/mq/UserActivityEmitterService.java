package ru.akozlovskiy.library.mq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import ru.akozlovskiy.library.domain.UserAcitvity;

@RequiredArgsConstructor
@Service
public class UserActivityEmitterService {

	private static Logger logger = LoggerFactory.getLogger(UserActivityEmitterService.class);

	private final RabbitTemplate rabbitTemplate;
	
	private final ObjectMapper objectMapper;

	private int activitiesCount = 0;

	@SneakyThrows
	public void emitAppUserActivity() {

		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = null;
		if (principal instanceof UserDetails) {
			username = ((UserDetails) principal).getUsername();
		} else {
			username = principal.toString();
		}

		UserAcitvity appUserActivity = new UserAcitvity(username, "TestActivity", ++activitiesCount);

		String userActivityJson = objectMapper.writeValueAsString(appUserActivity);

		logger.info("****** Send to RabbitMQ: {}", userActivityJson);

		rabbitTemplate.convertAndSend("all-activity-queue", userActivityJson);
	}
}
