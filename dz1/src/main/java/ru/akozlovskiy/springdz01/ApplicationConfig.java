package ru.akozlovskiy.springdz01;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.core.io.FileSystemResourceLoader;
import org.springframework.core.io.Resource;

import ru.akozlovskiy.springdz01.dao.QuestionDAO;
import ru.akozlovskiy.springdz01.dao.QuestionDAOImpl;
import ru.akozlovskiy.springdz01.service.AnswerHandlerService;
import ru.akozlovskiy.springdz01.service.ConsoleService;
import ru.akozlovskiy.springdz01.service.ConsoleServiceImpl;
import ru.akozlovskiy.springdz01.service.StudentTesterService;
import ru.akozlovskiy.springdz01.service.StudentTesterServiceImpl;

@Configuration
public class ApplicationConfig {

	@Bean
	AnswerHandlerService answerHandlerService() {
		return new ru.akozlovskiy.springdz01.service.AnswerHandlerServiceImpl();
	}

	@Bean
	StudentTesterService studentTesterService(AnswerHandlerService answerHandler, QuestionDAO questionDAO, ConsoleService consoleService) {
		return new StudentTesterServiceImpl(questionDAO, answerHandler, consoleService);
	}

	@Bean
	QuestionDAO questionDAO(@Value("classpath:Questions.csv") Resource resource) {
		return new QuestionDAOImpl(resource);
	}

	@Bean
	static PropertySourcesPlaceholderConfigurer initPopertySourcesPlaceholderConfigurer() {
		PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer = new PropertySourcesPlaceholderConfigurer();
		FileSystemResourceLoader fileSystemResourceLoader = new FileSystemResourceLoader();
		Resource location = fileSystemResourceLoader.getResource("TestStudentApplication.properties");
		propertySourcesPlaceholderConfigurer.setLocation(location);
		return propertySourcesPlaceholderConfigurer;
	}

	@Bean
	public MessageSource messageSource() {
		ReloadableResourceBundleMessageSource ms = new ReloadableResourceBundleMessageSource();
		ms.setBasename("/i18n/bundle");
		ms.setDefaultEncoding("UTF-8");
		return ms;
	}

	@Bean
	public ConsoleService consoleService() {
		return new ConsoleServiceImpl();
	}
}
