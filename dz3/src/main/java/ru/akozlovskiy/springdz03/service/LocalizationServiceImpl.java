package ru.akozlovskiy.springdz03.service;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import ru.akozlovskiy.springdz03.ApplicationSettings;

@Service
public class LocalizationServiceImpl implements LocalizationService {

	private final MessageSource messageSource;

	private final ApplicationSettings applicationSettings;

	@Autowired
	public LocalizationServiceImpl(MessageSource messageSource, ApplicationSettings applicationSettings) {
		this.messageSource = messageSource;
		this.applicationSettings = applicationSettings;
	}

	@Override
	public String getString(String stringCode) {
		Locale locale = new Locale(applicationSettings.getLocale());
		return messageSource.getMessage(stringCode, null, null, locale);
	}
}
