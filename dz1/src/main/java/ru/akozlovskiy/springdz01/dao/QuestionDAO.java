package ru.akozlovskiy.springdz01.dao;

import java.io.IOException;
import java.util.List;

import ru.akozlovskiy.springdz01.domain.Question;

/**
 * 
 *  ������ ��������� ������ ��������
 * @author akozlovskiy
 *
 */
public interface QuestionDAO {
	public List<Question> getQuestionList() throws IOException;
}
