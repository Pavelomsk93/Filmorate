package ru.yandex.practicum.filmorate.storage.friends;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.exceptions.UserNotFoundException;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.storage.user.UserDaoStorage;

import java.util.*;
import java.util.stream.Collectors;

@Component
@Slf4j
public class FriendsDbStorage implements FriendsDaoStorage {

    private final JdbcTemplate jdbcTemplate;
    private final UserDaoStorage userDaoStorage;

    public FriendsDbStorage(JdbcTemplate jdbcTemplate, UserDaoStorage userDaoStorage) {
        this.jdbcTemplate = jdbcTemplate;
        this.userDaoStorage = userDaoStorage;
    }

    @Override
    public void addFriend(int userId, int friendId) {
        if (userDaoStorage.getUsers().stream().noneMatch(u -> Objects.equals(u.getId(), userId))) {
            throw new UserNotFoundException("Идентификатор пользователя не найден");
        } else if (userDaoStorage.getUsers().stream().noneMatch(u -> Objects.equals(u.getId(), friendId))) {
            throw new UserNotFoundException("Идентификатор друга не найден");
        }
        String sql =
                "INSERT INTO FRIENDS (USER_ID, FRIEND_ID) " +
                        "VALUES (?, ?)";
        jdbcTemplate.update(sql, userId, friendId);
    }

    @Override
    public void deleteFriend(int userId, int friendId) {
        if (userDaoStorage.getUsers().stream().noneMatch(u -> Objects.equals(u.getId(), userId))) {
            throw new UserNotFoundException("Идентификатор пользователя не найден");
        } else if (userDaoStorage.getUsers().stream().noneMatch(u -> Objects.equals(u.getId(), friendId))) {
            throw new UserNotFoundException("Идентификатор друга не найден");
        }
        String sql =
                "DELETE " +
                        "FROM FRIENDS " +
                        "WHERE USER_ID = ? " +
                        "AND FRIEND_ID = ?";
        jdbcTemplate.update(sql, userId, friendId);
    }

    @Override
    public List<User> getAllFriendsUser(int id) {
        String sql =
                "SELECT FRIEND_ID " +
                        "FROM FRIENDS " +
                        "WHERE USER_ID=?";
        List<Integer> friendsUser = jdbcTemplate.queryForList(sql, Integer.class, id);
        return friendsUser.stream()
                .map(userDaoStorage::findById)
                .collect(Collectors.toList());
    }

}
