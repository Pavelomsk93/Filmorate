package ru.yandex.practicum.filmorate.storage.film_genres;

import ru.yandex.practicum.filmorate.model.Film;


public interface FilmGenresDaoStorage {

    void createGenreByFilm(Film film);

    void updateGenreFilm(Film film);

}
