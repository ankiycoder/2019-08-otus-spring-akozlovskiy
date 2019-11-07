package ru.akozlovskiy.springdz01.dao;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;

import au.com.bytecode.opencsv.CSVReader;
import au.com.bytecode.opencsv.bean.ColumnPositionMappingStrategy;
import au.com.bytecode.opencsv.bean.CsvToBean;
import ru.akozlovskiy.springdz01.domain.Question;
import ru.akozlovskiy.springdz01.service.LocalizationService;

public class QuestionDAOImpl implements QuestionDAO {

	private Resource resource;

	@Autowired
	private LocalizationService localizationService;

	@Value("${locale}")
	private String locale;

	public QuestionDAOImpl(Resource resource, LocalizationService localizationService) {
		this.resource = resource;
		this.localizationService = localizationService;
	}

	public List<Question> getQuestionList() {

		InputStream resourceAsStream = null;
		try {
			resourceAsStream = resource.getInputStream();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		InputStreamReader r = new InputStreamReader(resourceAsStream);

		BufferedReader br = new BufferedReader(r);

		CSVReader csvReader = new CSVReader(br, ';');

		CsvToBean<Question> csvToBean = new CsvToBean<>();

		List<Question> questionList = csvToBean.parse(setColumMapping(), csvReader);

		//convertToLocale(questionList);

		return questionList;
	}

	private void convertToLocale(List<Question> questionList) {
		questionList.stream().forEach(
				question -> question.setQuestionText(localizationService.getString(question.getQuestionText())));
	}

	private static ColumnPositionMappingStrategy<Question> setColumMapping() {
		ColumnPositionMappingStrategy<Question> strategy = new ColumnPositionMappingStrategy<>();
		strategy.setType(Question.class);
		String[] columns = new String[] { "questionText", "responses", "correctAnswerNumber" };
		strategy.setColumnMapping(columns);
		return strategy;
	}
}