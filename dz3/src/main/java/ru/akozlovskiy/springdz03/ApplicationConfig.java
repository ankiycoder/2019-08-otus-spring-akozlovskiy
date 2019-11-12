package ru.akozlovskiy.springdz03;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.core.io.ClassPathResource;

import ru.akozlovskiy.springdz03.dao.QuestionDAO;
import ru.akozlovskiy.springdz03.dao.QuestionDAOImpl;
import ru.akozlovskiy.springdz03.service.LocalizationService;

@Configuration
public class ApplicationConfig {

	@Bean
	QuestionDAO questionDAO(ApplicationSettings applicationSettings, LocalizationService localizationService) {
		String fileName = "Questions_" + applicationSettings.getLocale() + ".csv";
		ClassPathResource classPathResource = new ClassPathResource(fileName);
		return new QuestionDAOImpl(classPathResource);
	}

	@Bean
	MessageSource messageSource() {
		ReloadableResourceBundleMessageSource ms = new ReloadableResourceBundleMessageSource();
		ms.setBasename("/i18n/bundle");
		ms.setDefaultEncoding("UTF-8");
		return ms;
	}
}
