package ru.akozlovskiy.springdz03.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.core.io.Resource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import ru.akozlovskiy.springdz03.domain.Question;

@ExtendWith(SpringExtension.class)
public class QuestionDAOImplTest {

	@Mock
	private Resource resource;

	private QuestionDAO questionDAO;

	@BeforeEach
	public void initialize() throws IOException {
		String questionRecord = "What collection allows you to get an object by key; ArrayList,LinkedList,HashMap;  3";
		InputStream is = new ByteArrayInputStream(questionRecord.getBytes());
		Mockito.when(resource.getInputStream()).thenReturn(is);
	}

	@Test
	public void getQuestionListTest() throws IOException {
		questionDAO = new QuestionDAOImpl(resource);

		List<Question> questionList = questionDAO.getQuestionList();
		assertEquals(1, questionList.size(), "Wrong question list size");
	}
}