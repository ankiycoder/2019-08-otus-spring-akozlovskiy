package ru.akozlovskiy.springdz03.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.Resource;

import ru.akozlovskiy.springdz03.domain.Question;

@SpringBootTest()
public class AnswerHandlerServiceImplTest {
	@Mock
	private Resource resource;

	@Autowired
	private LocalizationService localizationService;

	private AnswerHandlerService answerHandlerService;

	@BeforeEach
	public void initialize() throws IOException {
		String questionRecord = "What collection allows you to get an object by key; ArrayList,LinkedList,HashMap;  3";
		InputStream is = new ByteArrayInputStream(questionRecord.getBytes());
		Mockito.when(resource.getInputStream()).thenReturn(is);
	}

	@Test
	public void printTestingResultOkResponse() throws IOException {
		answerHandlerService = new AnswerHandlerServiceImpl(localizationService);
		Question question = new Question();
		question.setCorrectAnswerNumber(3);
		question.setQuestionText("What collection allows you to get an object by key");
		question.setResponses("ArrayList,LinkedList,HashMap");
		answerHandlerService.addAnswer(question, 3);

		String result = answerHandlerService.printTestingResult();
		assertEquals("Question: What collection allows you to get an object by key -> OK\n", result);
	}

	@Test
	public void printTestingResultErrorResponse() throws IOException {
		answerHandlerService = new AnswerHandlerServiceImpl(localizationService);
		Question question = new Question();
		question.setCorrectAnswerNumber(3);
		question.setQuestionText("What collection allows you to get an object by key");
		question.setResponses("ArrayList,LinkedList,HashMap");
		answerHandlerService.addAnswer(question, 1);

		String result = answerHandlerService.printTestingResult();
		assertEquals("Question: What collection allows you to get an object by key -> Error\n", result);
	}
}
