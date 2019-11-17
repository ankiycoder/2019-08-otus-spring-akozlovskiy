package ru.akozlovskiy.springdz03;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import ru.akozlovskiy.springdz03.service.StudentTesterService;

@SpringBootApplication
public class TestStudentSpringApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(TestStudentSpringApplication.class, args);
		StudentTesterService tester = context.getBean(StudentTesterService.class);
		tester.test();
	}
}
