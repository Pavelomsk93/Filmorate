package ru.yandex.practicum.filmorate.testcontrollers;

import org.junit.jupiter.api.Test;
import ru.yandex.practicum.filmorate.controllers.UserController;
import ru.yandex.practicum.filmorate.exceptions.ValidationException;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.validate.Validate;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class UserControllerTest {

    @Test
    void shouldValidateCreateUserWithBlankEmail(){
        Validate validate = new Validate();
        assertThrows(
                ValidationException.class,
                validate::createUserWithBlankEmail
        );
    }
    @Test
    void shouldValidateCreateUserWithWrongEmail(){
        Validate validate = new Validate();
        assertThrows(
                ValidationException.class,
                validate::createUserWithWrongEmail
        );
    }
    @Test
    void shouldValidateCreateUserWithBlankLogin(){
        Validate validate = new Validate();
        assertThrows(
                ValidationException.class,
                validate::createUserWithBlankLogin
        );
    }
    @Test
    void shouldValidateCreateUserWithFutureBirthday(){
        Validate validate = new Validate();
        assertThrows(
                ValidationException.class,
                validate::createUserWithFutureBirthday
        );
    }
    @Test
    void shouldValidateCreateUserWithLoginWithSpaces(){
        Validate validate = new Validate();
        assertThrows(
                ValidationException.class,
                validate::createUserWithLoginWithSpaces
        );
    }
    @Test
    void shouldValidateUpdateUserWithWrongId(){
        Validate validate = new Validate();
        assertThrows(
                ValidationException.class,
                validate::updateUserWithWrongId
        );
    }
    @Test
    void shouldReplaceNameToLogin(){
        UserController controller = new UserController();
        User user = new User(0,"paveltomsk95@mail.ru","Pavel93"," ", LocalDate.of(1995,3,14));
        controller.createUser(user);
        assertEquals(user.getLogin(),user.getName());
    }
}

