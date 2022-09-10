package ru.yandex.practicum.filmorate.storage.film;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.exceptions.FilmNotFoundException;
import ru.yandex.practicum.filmorate.exceptions.ValidationException;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.service.FilmCustomValidator;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@Slf4j
public class InMemoryFilmStorage implements FilmStorage{

    private final Map<Integer, Film> films = new HashMap<>();
    private final FilmCustomValidator customValidator = new FilmCustomValidator();

    private int id = 0;



    @Override
    public List<Film> getFilms() {
        return new ArrayList<>(films.values());
    }

    @Override
    public Film createFilm(Film film) {
        if (films.containsKey(film.getId())) {
            log.error("Фильм: {} уже существует.", film.getName());
            throw new ValidationException("Фильм: " + film.getName() + " уже существует.");
        }else if(!customValidator.isValid(film)){
            log.info("Попытка добавить фильм с некорректной информацией");
            throw new ValidationException("Некорректно заполнено одно из полей");
        }else {
            film.setId(++id);
            films.put(film.getId(), film);
            log.info("Создали фильм: {}.", film);
            return film;
        }
    }

    @Override
    public Film updateFilm(Film film) {
        if (films.containsKey(film.getId())) {
            film.setLikes(films.get(film.getId()).getLikes());
            films.put(film.getId(), film);
            log.info("Обновили фильм: {}.", film);
            return film;
        }else {
            log.error("Фильм не найден");
            throw new FilmNotFoundException("Фильм не найден");
        }
    }

    @Override
    public Film findById(int id){
        if(films.containsKey(id)){
            return films.get(id);
        }else{
            log.error("Фильм с id {} не найден",id);
            throw new FilmNotFoundException(String.format("Фильм с id %d не найден",id));
        }
    }
}
