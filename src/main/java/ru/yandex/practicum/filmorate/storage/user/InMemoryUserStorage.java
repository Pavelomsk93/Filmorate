package ru.yandex.practicum.filmorate.storage.user;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.exceptions.UserNotFoundException;
import ru.yandex.practicum.filmorate.exceptions.ValidationException;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.service.UserCustomValidator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@Slf4j
public class InMemoryUserStorage implements UserStorage{

    private int id = 0;
    private final Map<Integer, User> users = new HashMap<>();
    private final UserCustomValidator customValidator = new UserCustomValidator();

    @Override
    public List<User> getUser() {
        return new ArrayList<>(users.values());
    }

    @Override
    public User createUser(User user) {
        if (users.containsKey(user.getId())) {
            log.error("Такой пользователь уже существует.");
            throw new ValidationException("Такой пользователь уже существует.");
        }else if(!customValidator.isValid(user)) {
            log.info("Попытка добавить пользователя с некорректной информацией");
            throw new ValidationException("Некорректно заполнено одно из полей");
        }else {
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
