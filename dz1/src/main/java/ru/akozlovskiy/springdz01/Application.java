package ru.akozlovskiy.springdz01;

import java.io.IOException;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import ru.akozlovskiy.springdz01.service.StudentTesterService;

@ComponentScan
@Configuration
public class Application {

	public static void main(String[] args) throws IOException {

		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Application.class);

		StudentTesterService tester = context.getBean(StudentTesterService.class);
		tester.test();
	}
}