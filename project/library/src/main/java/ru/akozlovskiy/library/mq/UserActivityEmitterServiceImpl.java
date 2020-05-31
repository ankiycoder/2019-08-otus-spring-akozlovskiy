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
import ru.akozlovskiy.library.domain.Book;
import ru.akozlovskiy.library.domain.UserRequest;

@RequiredArgsConstructor
@Service
public class UserActivityEmitterServiceImpl implements UserActivityEmitterService {

	private static Logger logger = LoggerFactory.getLogger(UserActivityEmitterService.class);

	private final RabbitTemplate rabbitTemplate;

	private final ObjectMapper objectMapper;

	@SneakyThrows
	@Override
	public void emitAppUserActivity(Book book) {

		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String userLogin = null;
		if (principal instanceof UserDetails) {
			userLogin = ((UserDetails) principal).getUsername();
		} else {
			userLogin = principal.toString();
		}

		UserRequest appUserActivity = new UserRequest(book, userLogin);

		String userActivityJson = objectMapper.writeValueAsString(appUserActivity);

		logger.info("****** Send to RabbitMQ message: {}", userActivityJson);

		rabbitTemplate.convertAndSend("user-request-queue", userActivityJson);
	}
}