package ru.akozlovskiy.springdz01;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.FileSystemResourceLoader;
import org.springframework.core.io.Resource;

import ru.akozlovskiy.springdz01.dao.QuestionDAO;
import ru.akozlovskiy.springdz01.dao.QuestionDAOImpl;
import ru.akozlovskiy.springdz01.service.AnswerHandlerService;
import ru.akozlovskiy.springdz01.service.StudentTesterService;
import ru.akozlovskiy.springdz01.service.StudentTesterServiceImpl;

@Configuration
public class ApplicationConfig {

	@Bean
	AnswerHandlerService answerHandlerService() {
		return new ru.akozlovskiy.springdz01.service.AnswerHandlerServiceImpl();
	}

	@Bean
	StudentTesterService studentTesterService(AnswerHandlerService answerHandler, QuestionDAO questionDAO) {
		return new StudentTesterServiceImpl(questionDAO, answerHandler);
	}

	@Bean
	QuestionDAO questionDAO(@Value("file:${questionFileUrl}") Resource resource) {
		return new QuestionDAOImpl(resource);
	}

	@Bean
	static PropertySourcesPlaceholderConfigurer propCfg() {
		PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer = new PropertySourcesPlaceholderConfigurer();
		FileSystemResourceLoader fileSystemResourceLoader = new FileSystemResourceLoader();
		Resource location = fileSystemResourceLoader.getResource("TestStudentApplication.properties");
		propertySourcesPlaceholderConfigurer.setLocation(location);
		return propertySourcesPlaceholderConfigurer;
	}
}
