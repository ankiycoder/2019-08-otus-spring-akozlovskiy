package ru.akozlovskiy.library.rabbitmq;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfig {

	@Bean
	public Queue allActivityQueue() {
		return new Queue("all-activity-queue");
	}

	@Bean
	public DirectExchange directExchange() {
		return new DirectExchange("main-exchange");
	}

	@Bean
	public Binding allActivityBinding() {
		return BindingBuilder.bind(allActivityQueue()).to(directExchange()).withQueueName();
	}
}
