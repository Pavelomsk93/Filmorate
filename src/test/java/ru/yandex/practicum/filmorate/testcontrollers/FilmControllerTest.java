package ru.yandex.practicum.filmorate.testcontrollers;

import org.junit.jupiter.api.Test;

import ru.yandex.practicum.filmorate.controllers.FilmController;
import ru.yandex.practicum.filmorate.exceptions.ValidationException;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.service.film.FilmService;
import ru.yandex.practicum.filmorate.storage.film.InMemoryFilmStorage;
import ru.yandex.practicum.filmorate.validate.Validate;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class FilmControllerTest {


    @Test
    void shouldValidateCreateFilmWithBlankName(){
        Validate validate = new Validate();
         assertThrows(
                ValidationException.class,
                validate::createFilmWithBlankName
        );
    }

    @Test
    void shouldValidateCreateFilmWithFailReleaseDate(){
        Validate validate = new Validate();
        final ValidationException exception = assertThrows(
                ValidationException.class,
                validate::createFilmWithFailReleaseDate
        );
        assertEquals("Дата релиза фильма не может быть раньше " + LocalDate.of(1895, 12, 28),exception.getMessage());
    }

    @Test
    void shouldValidateCreateFilmWithLongDescription(){
        Validate validate = new Validate();
        assertThrows(
                ValidationException.class,
                validate::createFilmWithLongDescription
        );
    }

    @Test
    void shouldValidateCreateFilmWithNegativeDuration(){
        Validate validate = new Validate();
        assertThrows(
                ValidationException.class,
                validate::createFilmWithNegativeDuration
        );
    }

    @Test
    void shouldCreateFilm(){
        FilmController controller = new FilmController(new FilmService(new InMemoryFilmStorage()));

        Film film = new Film(0,"Фильм 1","Описание первого фильма", LocalDate.of(1985,3,14),90);
        controller.createFilm(film);
        System.out.println(film);
        Film film2 = new Film(1,"Фильм 2","Описание обновлённого фильма", LocalDate.of(1985,3,14),90);
        controller.updateFilm(film2);
        System.out.println(film2);
        assertEquals(1,controller.getFilms().size());
    }
}
