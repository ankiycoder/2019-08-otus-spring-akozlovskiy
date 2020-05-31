package ru.akozlovskiy.userstat.rabbitmq;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfig {

	public static final String BOOK_REUEST_STAT_QUEUE_NAME = "user-request-queue";

	@Bean
	public Queue userRequestQueue() {
		return new Queue(BOOK_REUEST_STAT_QUEUE_NAME);
	}

	@Bean
	public DirectExchange directExchange() {
		return new DirectExchange("main-exchange");
	}

	@Bean
	public Binding allActivityBinding() {
		return BindingBuilder.bind(userRequestQueue()).to(directExchange()).withQueueName();
	}
}
