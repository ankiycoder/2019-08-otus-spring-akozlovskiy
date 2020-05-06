package ru.akozlovskiy.springdz14.batch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.stereotype.Component;

@Component
public class JobCompletionNotificationListener extends JobExecutionListenerSupport {

	private static final Logger log = LoggerFactory.getLogger(JobCompletionNotificationListener.class);

	@Override
	public void afterJob(JobExecution jobExecution) {
		log.info("------- Job Status = {}", jobExecution.getStatus());
	}

	@Override
	public void beforeJob(JobExecution jobExecution) {
		log.info("------- Job Status = {}", jobExecution.getStatus());
	}

}