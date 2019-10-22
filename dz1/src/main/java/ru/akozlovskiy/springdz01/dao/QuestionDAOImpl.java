package ru.akozlovskiy.springdz01.dao;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import org.springframework.core.io.Resource;

import au.com.bytecode.opencsv.CSVReader;
import au.com.bytecode.opencsv.bean.ColumnPositionMappingStrategy;
import au.com.bytecode.opencsv.bean.CsvToBean;
import ru.akozlovskiy.springdz01.domain.Question;

public class QuestionDAOImpl implements QuestionDAO {

	private Resource resource;

	public List<Question> getQuestionList() throws IOException {

		InputStream resourceAsStream = resource.getInputStream();

		InputStreamReader r = new InputStreamReader(resourceAsStream);

		BufferedReader br = new BufferedReader(r);

		CSVReader csvReader = new CSVReader(br, ';');

		CsvToBean<Question> csvToBean = new CsvToBean<>();

		return csvToBean.parse(setColumMapping(), csvReader);
	}

	private static ColumnPositionMappingStrategy<Question> setColumMapping() {
		ColumnPositionMappingStrategy<Question> strategy = new ColumnPositionMappingStrategy<>();
		strategy.setType(Question.class);
		String[] columns = new String[] { "questionText", "responses", "correctAnswerNumber" };
		strategy.setColumnMapping(columns);
		return strategy;
	}

	public Resource getResource() {
		return resource;
	}

	public void setResource(Resource resource) {
		this.resource = resource;
	}
}