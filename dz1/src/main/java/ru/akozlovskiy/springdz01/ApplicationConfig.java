package ru.akozlovskiy.springdz01;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResourceLoader;
import org.springframework.core.io.Resource;

import ru.akozlovskiy.springdz01.dao.QuestionDAO;
import ru.akozlovskiy.springdz01.dao.QuestionDAOImpl;
import ru.akozlovskiy.springdz01.service.AnswerHandlerService;
import ru.akozlovskiy.springdz01.service.ConsoleService;
import ru.akozlovskiy.springdz01.service.ConsoleServiceImpl;
import ru.akozlovskiy.springdz01.service.LocaleService;
import ru.akozlovskiy.springdz01.service.LocaleServiceImpl;
import ru.akozlovskiy.springdz01.service.LocalizationService;
import ru.akozlovskiy.springdz01.service.LocalizationServiceImpl;
import ru.akozlovskiy.springdz01.service.StudentTesterService;
import ru.akozlovskiy.springdz01.service.StudentTesterServiceImpl;

@Configuration
public class ApplicationConfig {

	@Bean
	AnswerHandlerService answerHandlerService(LocalizationService localizationService) {
		return new ru.akozlovskiy.springdz01.service.AnswerHandlerServiceImpl(localizationService);
	}

	@Bean
	StudentTesterService studentTesterService(AnswerHandlerService answerHandler, QuestionDAO questionDAO,
			ConsoleService consoleService, LocalizationService localizationService) {
		return new StudentTesterServiceImpl(questionDAO, answerHandler, consoleService, localizationService);
	}

	@Bean
	QuestionDAO questionDAO(LocaleService localeService, LocalizationService localizationService) {
		String fileName = "Questions_" + localeService.getLocale() + ".csv";
		ClassPathResource classPathResource = new ClassPathResource(fileName);
		return new QuestionDAOImpl(classPathResource);
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
	MessageSource messageSource() {
		ReloadableResourceBundleMessageSource ms = new ReloadableResourceBundleMessageSource();
		ms.setBasename("/i18n/bundle");
		ms.setDefaultEncoding("UTF-8");
		return ms;
	}

	//Бины создаются через @Service
	/*@Bean
	ConsoleService consoleService(LocalizationService localizationService) {
		return new ConsoleServiceImpl(localizationService);
	}

	@Bean
	LocalizationService localizationService(MessageSource messageSource, LocaleService localeService) {
		return new LocalizationServiceImpl(messageSource, localeService);
	}

	@Bean
	LocaleService localeService() {
		return new LocaleServiceImpl();
	}*/
}
