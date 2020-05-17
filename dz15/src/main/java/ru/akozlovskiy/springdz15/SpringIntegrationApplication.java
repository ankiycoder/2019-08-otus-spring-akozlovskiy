package ru.akozlovskiy.springdz15;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import ru.akozlovskiy.springdz15.domain.Document;
import ru.akozlovskiy.springdz15.integration.DocumentProcessorGateway;

@SpringBootApplication
public class SpringIntegrationApplication {

	public static void main(String[] args) {

		AbstractApplicationContext ctx = new AnnotationConfigApplicationContext(SpringIntegrationApplication.class);

		DocumentProcessorGateway docProcessor = ctx.getBean(DocumentProcessorGateway.class);
		for (int i = 0; i < 100; i++) {
			Document document = new Document();
			document.setComment("Generate document");
			docProcessor.process(document);
		}
	}
}
