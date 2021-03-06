package ru.akozlovskiy.springdz03;

import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.core.io.ClassPathResource;

@Configuration
public class ApplicationConfig {

	@Bean
	MessageSource messageSource() {
		ReloadableResourceBundleMessageSource ms = new ReloadableResourceBundleMessageSource();
		ms.setBasename("/i18n/bundle");
		ms.setDefaultEncoding("UTF-8");
		return ms;
	}

	@Bean
	ClassPathResource getClassPathResource(ApplicationSettings applicationSettings) {
		String fileName = "Questions_" + applicationSettings.getLocale() + ".csv";
		ClassPathResource classPathResource = new ClassPathResource(fileName);

		// Если файл не найден, то по дефолту используем английскую локаль
		if (!classPathResource.exists()) {
			Locale defaultLocale = Locale.ENGLISH;
			Locale.setDefault(defaultLocale);
			fileName = "Questions_" + defaultLocale.getLanguage() + ".csv";
			classPathResource = new ClassPathResource(fileName);
		}
		return classPathResource;
	}
}
