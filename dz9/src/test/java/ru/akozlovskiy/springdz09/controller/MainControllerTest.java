package ru.akozlovskiy.springdz09.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import com.baeldung.crud.entities.User;

import ru.akozlovskiy.springdz09.domain.repository.AuthorRepository;
import ru.akozlovskiy.springdz09.domain.repository.GenreRepository;
import ru.akozlovskiy.springdz09.domain.service.BookService;

public class MainControllerTest {
	
	private final BookService bookService;

	private final GenreRepository genreRepository;

	private final AuthorRepository authorRepository;
	
    private static Model mockedModel;

    @BeforeClass
    public static void setUpUserControllerInstance() {
        mockedUserRepository = mock(UserRepository.class);
        mockedBindingResult = mock(BindingResult.class);
        mockedModel = mock(Model.class);
        userController = new UserController(mockedUserRepository);
    }
    
    @Test
    public void whenCalledshowSignUpForm_thenCorrect() {
        User user = new User("John", "john@domain.com");

        assertThat(userController.showSignUpForm(user)).isEqualTo("add-user");
    }

}
