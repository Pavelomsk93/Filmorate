package ru.yandex.practicum.filmorate.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.exceptions.ValidationException;
import ru.yandex.practicum.filmorate.model.Film;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Slf4j
@RequestMapping("/films")
public class FilmController {

    private final Map<Integer, Film> films = new HashMap<>();
    private static final LocalDate DATE_RELEASE = LocalDate.of(1895, 12, 28);

    private int id = 0;

    @GetMapping
    public List<Film> getFilms() {
        return new ArrayList<>(films.values());
    }

    @PostMapping
    public Film createFilm(@Valid @RequestBody Film film) {
        if (films.containsKey(film.getId())) {
            log.error("Фильм: {} уже существует.",film.getName());
            throw new ValidationException("Фильм: " + film.getName() + " уже существует.");
        } else if (film.getReleaseDate().isBefore(DATE_RELEASE)) {
            log.error("Дата релиза фильма не может быть раньше " + DATE_RELEASE);
            throw new ValidationException("Дата релиза фильма не может быть раньше " + DATE_RELEASE);
        }else if(film.getDescription().length()>200) {
            log.error("Длина описания фильма не может быть больше 200 символов");
            throw new ValidationException("Длина описания фильма не может быть больше 200 символов");
        }else if(film.getDuration()<0) {
            log.error("Продолжительность фильма должна быть больше 0");
            throw new ValidationException("Продолжительность фильма должна быть больше 0");
        }else if(film.getName().isBlank()){
            log.error("Название не может быть пустым");
            throw new ValidationException("Название не может быть пустым");
        }else {
            film.setId(++id);
            films.put(film.getId(), film);
            log.info("Создали фильм: {}.", film);
            return film;
        }
    }

    @PutMapping
    public Film updateFilm(@Valid @RequestBody Film film) {
        if (films.containsKey(film.getId())) {
            films.put(film.getId(), film);
            log.info("Обновили фильм: {}.", film);
            return film;
        }else {
            log.error("Фильм не найден");
            throw new ValidationException("Фильм не найден");
        }
    }
}
