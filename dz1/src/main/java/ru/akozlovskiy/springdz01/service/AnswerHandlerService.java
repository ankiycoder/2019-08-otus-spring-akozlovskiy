package ru.akozlovskiy.springdz01.service;

import ru.akozlovskiy.springdz01.domain.Question;

/**
 * 
 * ������ ��������� �������
 * @author akozlovskiy
 *
 */
public interface AnswerHandlerService {

	/** ��������� ������ � ���������� �� ���� ����� 
	 * @param question
	 * @param answerNumber
	 */
	public void addAnswer(Question question, int answerNumber);

	/**
	 * ������������� ���������� �����
	 */
	public void printTestingResult();
	
}
