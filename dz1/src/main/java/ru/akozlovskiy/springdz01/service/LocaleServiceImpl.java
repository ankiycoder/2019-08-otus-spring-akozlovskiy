package ru.akozlovskiy.springdz01.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class LocaleServiceImpl implements LocaleService {

	@Value("${locale}")
	String locale;

	@Override
	public String getLocale() {
		return locale;
	}

}
