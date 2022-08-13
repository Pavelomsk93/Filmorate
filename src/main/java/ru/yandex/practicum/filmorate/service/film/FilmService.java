package ru.yandex.practicum.filmorate.service.film;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.storage.film.FilmStorage;

import java.util.List;

@Service
public class FilmService {

    FilmStorage filmStorage;

    @Autowired
    public FilmService(FilmStorage filmStorage){
        this.filmStorage = filmStorage;
    }

    public List<Film> findAll(){
        return filmStorage.getFilms();
    }

    public Film createFilm(Film film){
        return filmStorage.createFilm(film);
    }

    public Film updateFilm(Film film){
        return filmStorage.updateFilm(film);
    }

    public Film findById(int id){
        return filmStorage.findById(id);
    }
}
