package ru.yandex.practicum.filmorate.validate;

import lombok.NoArgsConstructor;
import ru.yandex.practicum.filmorate.controllers.FilmController;
import ru.yandex.practicum.filmorate.controllers.UserController;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.model.User;

import java.time.LocalDate;

@NoArgsConstructor
public class Validate {



    public void createFilmWithNullName(){
        FilmController controller = new FilmController();

        Film film = new Film(0,null,"Описание первого фильма", LocalDate.of(1985,3,14),90);
        controller.createFilm(film);
    }

    public void createFilmWithFailReleaseDate(){
        FilmController controller = new FilmController();

        Film film = new Film(0,"Фильм 1","Описание первого фильма", LocalDate.of(1785,3,14),90);
        controller.createFilm(film);
    }

    public void createFilmWithNullDescription(){
        FilmController controller = new FilmController();

        Film film = new Film(0,"Фильм 1",null, LocalDate.of(1985,3,14),90);
        controller.createFilm(film);
    }

    public void createFilmWithLongDescription(){
        FilmController controller = new FilmController();
        String description = "description";
        String description2 = description.repeat(20);

        Film film = new Film(0,"Фильм 1",description2, LocalDate.of(1985,3,14),90);
        controller.createFilm(film);
    }

    public void createFilmWithNullReleaseDate(){
        FilmController controller = new FilmController();

        Film film = new Film(0,"Фильм 1","Описание первого фильма", null,90);
        controller.createFilm(film);
    }

    public void createFilmWithNegativeDuration(){
        FilmController controller = new FilmController();

        Film film = new Film(0,"Фильм 1","Описание первого фильма", LocalDate.of(1985,3,14),-10);
        controller.createFilm(film);
    }

    public void createUserWithNullEmail(){
        UserController controller = new UserController();

        User user = new User(0,null,"Pavel93","Pavel",LocalDate.of(1993,3,14));
        controller.createUser(user);
    }

    public void createUserWithWrongEmail(){
        UserController controller = new UserController();

        User user = new User(0,"pavelomsk93","Pavel93","Pavel",LocalDate.of(1993,3,14));
        controller.createUser(user);
    }

    public void createUserWithNullLogin(){
        UserController controller = new UserController();

        User user = new User(0,"paveltomsk95@mail.ru",null,"Pavel",LocalDate.of(1995,3,14));
        controller.createUser(user);
    }

    public void createUserWithNullBirthday(){
        UserController controller = new UserController();

        User user = new User(0,"paveltomsk95@mail.ru","Pavel95","Pavel",null);
        controller.createUser(user);
    }

    public void createUserWithFutureBirthday(){
        UserController controller = new UserController();

        User user = new User(0,"paveltomsk95@mail.ru","Pavel95","Pavel",LocalDate.of(2025,4,2));
        controller.createUser(user);
    }

    public void createUserWithLoginWithSpaces(){
        UserController controller = new UserController();

        User user = new User(0,"paveltomsk95@mail.ru","Pavel 95","Pavel",LocalDate.of(1995,4,2));
        controller.createUser(user);
    }

    public void updateUserWithWrongId(){
        UserController controller = new UserController();

        User user = new User(0,"paveltomsk95@mail.ru","Pavel95","Pavel",LocalDate.of(1995,4,2));
        controller.createUser(user);

        User user2 = new User(2,"paveltomsk93@mail.ru","Pavel93","Pavel",LocalDate.of(1995,4,2));
        controller.updateUser(user2);
    }






}
