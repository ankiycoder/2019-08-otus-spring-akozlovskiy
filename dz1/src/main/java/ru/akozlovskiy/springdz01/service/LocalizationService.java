package ru.akozlovskiy.springdz01.service;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

@Service
public class LocalizationService {

	@Autowired
	MessageSource messageSource;

	@Value("${locale}")
	String locale;

	public String getString(String stringCode) {
		return messageSource.getMessage(stringCode, null, null, new Locale(locale, "RU"));
	}

}
