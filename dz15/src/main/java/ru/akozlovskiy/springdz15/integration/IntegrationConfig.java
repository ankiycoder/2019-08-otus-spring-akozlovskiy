package ru.akozlovskiy.springdz15.integration;

import java.io.File;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.annotation.Transformer;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.file.FileWritingMessageHandler;
import org.springframework.integration.file.support.FileExistsMode;
import org.springframework.messaging.MessageHandler;

import ru.akozlovskiy.springdz15.domain.Document;
import ru.akozlovskiy.springdz15.domain.repository.DocumentRepository;
import ru.akozlovskiy.springdz15.domain.service.SampleDocumentService;

@Configuration
@IntegrationComponentScan
public class IntegrationConfig {

	private static final String FILENAME_HEADER_PROPERTY = "FILE_NAME";

	// Точка входа - полученные документы сохранятся в БД
	@Bean
	public IntegrationFlow saveDocumentFlow(DocumentRepository documentRepository,
			SampleDocumentService serviceSample) {
		return f -> f.channel("documentFlow.input").handle(documentRepository, "save").channel("saveToFileFlow.input");

	}

	// Новый поток интеграции, который получает данные из канала и сохраняет их в
	// файл. В имени файли есть ID документа.

	@Bean
	public IntegrationFlow saveToFileFlow(SampleDocumentService sampleDocumentService) {
		return flow -> flow.enrichHeaders(s -> s.headerExpressions(c -> c.put(FILENAME_HEADER_PROPERTY, "payload.id")))
				.handle(sampleDocumentService, "doWork").<Document, String>transform(payload -> transferData(payload))
				.handle(targetDirectory());
	}

	@Bean
	@ServiceActivator(inputChannel = "fileChannel")
	public MessageHandler targetDirectory() {
		File destinationDirectory = new File("UnloadingDir");
		FileWritingMessageHandler handler = new FileWritingMessageHandler(destinationDirectory);
		handler.setFileExistsMode(FileExistsMode.REPLACE);
		handler.setExpectReply(false);
		handler.setFileNameGenerator(
				message -> "DocumentId-" + message.getHeaders().get(FILENAME_HEADER_PROPERTY) + ".txt");
		handler.setDeleteSourceFiles(true);
		return handler;
	}

	@Transformer
	public String transferData(Document document) {
		StringBuilder strb = new StringBuilder();
		strb.append("ID = ").append(document.getId()).append(", Comment = ").append(document.getComment());
		return strb.toString();
	}
}