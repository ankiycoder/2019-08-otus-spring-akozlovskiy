package ru.akozlovskiy.springdz15.domain.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import ru.akozlovskiy.springdz15.domain.Document;

@Service
public class ServiceSampleImpl implements ServiceSample {
	
	@Value("${fortest}")
	private String fortest;
	
	@Override
	public void doWork(Document Document) {
		System.out.print(" ---------- cal ServiceSampleImpl.dowork === " + fortest);
		
	}

}
