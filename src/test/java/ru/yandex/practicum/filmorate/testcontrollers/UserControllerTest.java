package ru.yandex.practicum.filmorate.testcontrollers;

import org.junit.jupiter.api.Test;
import ru.yandex.practicum.filmorate.controllers.FilmController;
import ru.yandex.practicum.filmorate.controllers.UserController;
import ru.yandex.practicum.filmorate.exceptions.UserNotFoundException;
import ru.yandex.practicum.filmorate.exceptions.ValidationException;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.service.film.FilmService;
import ru.yandex.practicum.filmorate.service.user.UserService;
import ru.yandex.practicum.filmorate.storage.film.InMemoryFilmStorage;
import ru.yandex.practicum.filmorate.storage.user.InMemoryUserStorage;
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
                UserNotFoundException.class,
                validate::updateUserWithWrongId
        );
    }
    @Test
    void shouldReplaceNameToLogin(){
        UserController controller = new UserController(new UserService(new InMemoryUserStorage()));
        User user = new User(0,"paveltomsk95@mail.ru","Pavel93"," ", LocalDate.of(1995,3,14));
        controller.createUser(user);
        assertEquals(user.getLogin(),user.getName());
    }

    @Test
    void shouldNewTest(){
        UserController controller = new UserController(new UserService(new InMemoryUserStorage()));
        FilmController filmController = new FilmController(new FilmService(new InMemoryFilmStorage()));
        User user = new User(0,"paveltomsk95@mail.ru","Pavel95"," Ivano", LocalDate.of(1995,3,14));
        controller.createUser(user);
        User user2 = new User(0,"paveltomsk94@mail.ru","Pavel93","Pavelo",LocalDate.of(1993,3,14));
        controller.createUser(user2);
        User user3 = new User(0,"paveltomsk91@mail.ru","Pavel94"," Ivan", LocalDate.of(1995,3,14));
        controller.createUser(user3);
        User user4 = new User(0,"paveltomsk92@mail.ru","Pavel99","Pavel",LocalDate.of(1993,3,14));
        controller.createUser(user4);
        controller.addFriends(1,2);
        controller.addFriends(1,3);
        controller.addFriends(1,4);
        System.out.println(controller.findBId(1).getFriends());
        User user5 = new User(1,"paveltomsk92@mail.ru","Pavel999999","Pavel",LocalDate.of(1993,3,14));
        controller.updateUser(user5);
        System.out.println(controller.findBId(1).getFriends());
        Film film = new Film(0,"Фильм 1","Описание первого фильма", LocalDate.of(1985,3,14),90);
        filmController.createFilm(film);
        filmController.putLike(1,1);
        filmController.putLike(1,2);
        filmController.putLike(1,3);
        filmController.putLike(1,4);
        System.out.println(filmController.findById(1).getLikes());
        Film film2 = new Film(1,"Фильм 12","Описание первого фильма2", LocalDate.of(1985,3,14),90);
        filmController.updateFilm(film2);
        System.out.println(filmController.findById(1).getLikes());
        assertEquals(3,controller.getAllFriends(1).size());
    }
}

