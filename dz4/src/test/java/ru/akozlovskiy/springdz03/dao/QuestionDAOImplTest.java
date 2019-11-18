package ru.akozlovskiy.springdz03.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.io.ClassPathResource;

import ru.akozlovskiy.springdz03.domain.Question;

@SpringBootTest()
public class QuestionDAOImplTest {

	@MockBean
	private ClassPathResource classPathResource;

	@Autowired
	private QuestionDAO questionDAO;

	@BeforeEach
	public void initialize() throws IOException {
		String questionRecord = "What collection allows you to get an object by key;ArrayList,LinkedList,HashMap;  3";
		InputStream is = new ByteArrayInputStream(questionRecord.getBytes());
		Mockito.when(classPathResource.getInputStream()).thenReturn(is);
	}

	@Test
	public void getQuestionListTest() throws IOException {
		List<Question> questionList = questionDAO.getQuestionList();
		assertEquals(1, questionList.size(), "Wrong question list size");

		Question question = questionList.get(0);
		assertEquals(3, question.getCorrectAnswerNumber(), "Wrong correct answer number");
		assertEquals("What collection allows you to get an object by key", question.getQuestionText(), "Wrong question text");
		assertEquals("ArrayList,LinkedList,HashMap", question.getResponses(), "Wrong responses");
	}
}