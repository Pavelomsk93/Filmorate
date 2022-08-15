package ru.yandex.practicum.filmorate.service.film;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.exceptions.FilmNotFoundException;
import ru.yandex.practicum.filmorate.exceptions.UserNotFoundException;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.storage.film.FilmStorage;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
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

    public void putLike(int id,int userId){
        if(filmStorage.getFilms().contains(filmStorage.findById(id))){
            log.info("Пользователь {} поставил лайк фильму {}.",userId,filmStorage.findById(id).getName());
            filmStorage.findById(id).getLikes().add(userId);
        }else{
            log.error("Фильм не найден");
            throw new FilmNotFoundException("Фильм не найден");
        }
    }

    public void removeLike(int id,int userId){
        if(filmStorage.findById(id).getLikes().contains(userId)){
            filmStorage.findById(id).getLikes().remove(userId);
        }else{
            throw new UserNotFoundException(String.format("Пользователь %d не ставил лайк фильму %S",userId,filmStorage.findById(id).getName()));
        }
    }

    public List<Film> popularFilms(int count){
        return filmStorage.getFilms().stream().sorted((p0, p1) -> p1.getLikes().size() - (p0.getLikes().size()))
                .limit(count).collect(Collectors.toList());
    }


}
