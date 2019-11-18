package ru.akozlovskiy.springdz03.shell;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.shell.Shell;
import org.springframework.test.annotation.DirtiesContext;

import ru.akozlovskiy.springdz03.service.StudentTesterService;

@DisplayName("Тест команды shell")
@SpringBootTest
public class TestStudentApplicationShellCommandsTest {

	private static final String START_TESTING_COMMAND = "start";

	@MockBean
	private StudentTesterService studentTesterService;

	@Autowired
	private Shell shell;

	@DisplayName("Должна запускать studentTesterService.test")
	@DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
	@Test
	void shouldReturnExpectedMessageAndFirePublishMethodAfterPublishCommandEvaluated() {
		shell.evaluate(() -> START_TESTING_COMMAND);
		verify(studentTesterService, times(1)).test();
	}
}
