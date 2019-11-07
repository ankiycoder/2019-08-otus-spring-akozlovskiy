package ru.akozlovskiy.springdz01.service;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

@Service
public class LocalizationServiceImpl implements LocalizationService {

	@Autowired
	private MessageSource messageSource;

	@Value("${locale}")
	private String locale;

	@Override
	public String getString(String stringCode) {
		return messageSource.getMessage(stringCode, null, null, new Locale(locale, "RU"));
	}
}
