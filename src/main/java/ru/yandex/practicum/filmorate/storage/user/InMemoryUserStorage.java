package ru.yandex.practicum.filmorate.storage.user;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.exceptions.UserNotFoundException;
import ru.yandex.practicum.filmorate.exceptions.ValidationException;
import ru.yandex.practicum.filmorate.model.User;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@Slf4j
public class InMemoryUserStorage implements UserStorage{

    private int id = 0;
    private final Map<Integer, User> users = new HashMap<>();

    @Override
    public List<User> getUser() {
        return new ArrayList<>(users.values());
    }

    @Override
    public User createUser(User user) {
        if (users.containsKey(user.getId())) {
            log.error("Такой пользователь уже существует.");
            throw new ValidationException("Такой пользователь уже существует.");
        }else if(user.getBirthday().isAfter(LocalDate.now())) {
            log.error("Дата рождения не может быть в будущем");
            throw new ValidationException("Дата рождения не может быть в будущем");
        }else if(!user.getEmail().contains("@")) {
            log.error("Email должен содержать @");
            throw new ValidationException("Email должен содержать @");
        }else if(user.getLogin().contains(" ")) {
            log.error("Логин не может содержать пробел");
            throw new ValidationException("Логин не может содержать пробел");
        }else if(user.getEmail().isBlank()) {
            log.error("Email не может быть пустым");
            throw new ValidationException("Email не может быть пустым");
        }else if(user.getLogin().isBlank()) {
            log.error("Логин не может быть пустым");
            throw new ValidationException("Логин не может быть пустым");
        } else {
            if (user.getName().isBlank()) {
                log.debug("Имя не указано. В качестве имени используется логин.");
                user.setName(user.getLogin());
            }
            user.setId(++id);
            users.put(user.getId(), user);
            log.info("Создали пользователя: {}.", user);
            return user;
        }
    }

    @Override
    public User updateUser(User user) {
        if (users.containsKey(user.getId())) {
            user.setFriends(users.get(user.getId()).getFriends());
            users.put(user.getId(), user);
            log.info("Обновили пользователя: {}.", user);
            return user;
        } else {
            log.error("Id пользователя не найдено.");
            throw new UserNotFoundException("Id пользователя не найдено.");
        }
    }
    @Override
    public User findById(int id){
        if(users.containsKey(id)){
            return users.get(id);
        }else{
            log.error("Пользователь с id {} не найден",id);
            throw new UserNotFoundException(String.format("Пользователь с id %d не найден",id));
        }
    }


}
