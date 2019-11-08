package ru.akozlovskiy.springdz01.service;

import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

@Service
public class LocalizationServiceImpl implements LocalizationService {

	private final MessageSource messageSource;
	private final LocaleService localeService;

	public LocalizationServiceImpl(MessageSource messageSource, LocaleService localeService) {
		this.messageSource = messageSource;
		this.localeService = localeService;
	}

	@Override
	public String getString(String stringCode) {
		return messageSource.getMessage(stringCode, null, null, new Locale(localeService.getLocale(), "RU"));
	}
}
