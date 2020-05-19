package ru.akozlovskiy.springdz15.domain.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import ru.akozlovskiy.springdz15.domain.Document;

@Service
public class SampleDocumentServiceImpl implements SampleDocumentService {

	private final Logger logger = LoggerFactory.getLogger(SampleDocumentServiceImpl.class);

	public SampleDocumentServiceImpl() {

	}

	@Override
	public Document doWork(Document document) {
		logger.info("Process document with id = {} , something done...", document.getId());
		return document;
	}
}
