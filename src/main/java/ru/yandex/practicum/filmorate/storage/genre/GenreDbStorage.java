package ru.yandex.practicum.filmorate.storage.genre;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.exceptions.EntityNotFoundException;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.model.Genre;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class GenreDbStorage implements GenreDaoStorage {

    private final JdbcTemplate jdbcTemplate;

    public GenreDbStorage(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Genre getGenreById(int genreId) {
        if (genreId < 1) {
            throw new EntityNotFoundException("Введен некорректный идентификатор жанра.");
        }
        String sql =
                "SELECT * " +
                        "FROM GENRES " +
                        "WHERE GENRE_ID = ?";
        return jdbcTemplate.query(sql, (rs, rowNum) -> makeGenre(rs), genreId)
                .stream().findAny().orElse(null);
    }

    @Override
    public List<Genre> getAllGenres() {
        String sql =
                "SELECT * " +
                        "FROM GENRES " +
                        "ORDER BY GENRE_ID";
        return jdbcTemplate.query(sql, (rs, rowNum) -> makeGenre(rs));
    }

    @Override
    public Set<Genre> getGenresByFilm(Film film) {
        String sql =
                "SELECT GEN.GENRE_ID, GEN.NAME " +
                        "FROM GENRES GEN " +
                        "NATURAL JOIN FILMS_GENRES fg " +
                        "WHERE fg.FILM_ID = ?";
        return new HashSet<>(jdbcTemplate.query(sql, (rs, rowNum) -> makeGenre(rs), film.getId()));
    }

    public void updateGenreFilm(Film film) {
        String sql = "DELETE FROM FILMS_GENRES WHERE FILM_ID = ?";
        jdbcTemplate.update(sql, film.getId());
    }

    private Genre makeGenre(ResultSet rs) throws SQLException {
        return new Genre(rs.getInt("GENRE_ID"), rs.getString("NAME"));
    }
}
