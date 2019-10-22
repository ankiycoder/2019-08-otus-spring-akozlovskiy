package ru.akozlovskiy.springdz01;

import java.io.IOException;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import ru.akozlovskiy.springdz01.service.Tester;

public class Application {

	public static void main(String[] args) throws IOException {

		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("/spring-context.xml");

		Tester tester = context.getBean(Tester.class);
		tester.testing();
	}
}