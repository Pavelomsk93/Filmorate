package ru.yandex.practicum.filmorate.storage.film;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.exceptions.FilmNotFoundException;
import ru.yandex.practicum.filmorate.exceptions.ValidationException;
import ru.yandex.practicum.filmorate.model.Film;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@Slf4j
public class InMemoryFilmStorage implements FilmStorage{

    private final Map<Integer, Film> films = new HashMap<>();
    private static final LocalDate DATE_RELEASE = LocalDate.of(1895, 12, 28);

    private int id = 0;



    @Override
    public List<Film> getFilms() {
        return new ArrayList<>(films.values());
    }

    @Override
    public Film createFilm(Film film) {
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

    @Override
    public Film updateFilm(Film film) {
        if (films.containsKey(film.getId())) {
            films.put(film.getId(), film);
            log.info("Обновили фильм: {}.", film);
            return film;
        }else {
            log.error("Фильм не найден");
            throw new ValidationException("Фильм не найден");
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
