package ru.yandex.practicum.filmorate.testcontrollers;

import org.junit.jupiter.api.Test;
import ru.yandex.practicum.filmorate.controllers.UserController;
import ru.yandex.practicum.filmorate.exceptions.UserNotFoundException;
import ru.yandex.practicum.filmorate.exceptions.ValidationException;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.service.user.UserService;
import ru.yandex.practicum.filmorate.storage.user.InMemoryUserStorage;
import ru.yandex.practicum.filmorate.validate.Validate;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

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
        assertEquals(3,controller.getAllFriends(1).size());
    }

    @Test
    void shouldNewTest2(){
        Set<Integer> one = new HashSet<>();
        Set<Integer> two = new HashSet<>();
        one.add(1);
        one.add(3);
        one.add(7);
        one.add(5);
        two.add(1);
        two.add(7);
        Set<Integer> common = new HashSet<>(two);
        common.retainAll(one);
        System.out.println(common);
        assertEquals(2,common.size());

    }
}

