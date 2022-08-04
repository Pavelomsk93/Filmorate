package ru.yandex.practicum.filmorate.testcontrollers;

import org.junit.jupiter.api.Test;

import ru.yandex.practicum.filmorate.exceptions.ValidationException;
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
}