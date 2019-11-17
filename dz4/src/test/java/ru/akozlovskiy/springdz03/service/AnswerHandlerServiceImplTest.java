package ru.akozlovskiy.springdz03.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import ru.akozlovskiy.springdz03.domain.Question;

@SpringBootTest()
public class AnswerHandlerServiceImplTest {

	private static final String QUESTION_TEXT = "What collection allows you to get an object by key";

	@MockBean
	private LocalizationService localizationService;

	@Autowired
	private AnswerHandlerService answerHandlerService;

	@BeforeEach
	public void initialize() throws IOException {
		Mockito.when(localizationService.getString("response.ok")).thenReturn("OK");
		Mockito.when(localizationService.getString("response.error")).thenReturn("Error");
		Mockito.when(localizationService.getString("question")).thenReturn("Question: ");
	}

	@DisplayName("Распечатка успешного ответа")
	@Test
	public void printTestingResultOkResponse() throws IOException {
		Question question = new Question();
		question.setCorrectAnswerNumber(3);
		question.setQuestionText(QUESTION_TEXT);
		answerHandlerService.addAnswer(question, 3);

		String result = answerHandlerService.printTestingResult();
		assertEquals("Question: " + QUESTION_TEXT + " -> OK\n", result);
	}

	@DisplayName("Распечатка ошибочного ответа")
	@Test
	public void printTestingResultErrorResponse() throws IOException {
		answerHandlerService = new AnswerHandlerServiceImpl(localizationService);
		Question question = new Question();
		question.setCorrectAnswerNumber(3);
		question.setQuestionText(QUESTION_TEXT);
		answerHandlerService.addAnswer(question, 1);

		String result = answerHandlerService.printTestingResult();
		assertEquals("Question: " + QUESTION_TEXT + " -> Error\n", result);
	}
}
