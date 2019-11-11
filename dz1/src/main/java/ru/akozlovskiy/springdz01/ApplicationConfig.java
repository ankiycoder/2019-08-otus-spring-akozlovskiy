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
import ru.akozlovskiy.springdz01.service.LocaleService;
import ru.akozlovskiy.springdz01.service.LocalizationService;

@Configuration
public class ApplicationConfig {

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
}
