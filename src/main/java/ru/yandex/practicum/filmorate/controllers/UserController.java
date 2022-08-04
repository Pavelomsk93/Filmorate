package ru.yandex.practicum.filmorate.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.exceptions.ValidationException;
import ru.yandex.practicum.filmorate.model.User;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Slf4j
@RequestMapping("/users")
public class UserController {

    private int id = 0;
    private final Map<Integer, User> users = new HashMap<>();

    @GetMapping
    public List<User> getUser() {
        return new ArrayList<>(users.values());
    }

    @PostMapping
    public User createUser(@Valid @RequestBody User user){
        if (users.containsKey(user.getId())) {
            log.error("Такой пользователь уже существует.");
            throw new ValidationException("Такой пользователь уже существует.");
        }else if(user.getLogin().contains(" ")) {
            log.error("Логин не может содержать пробелы");
            throw new ValidationException("Логин не может содержать пробелы");
        }else if(user.getBirthday().isAfter(LocalDate.now())) {
            log.error("Дата рождения не может быть в будущем");
            throw new ValidationException("Дата рождения не может быть в будущем");
        }else if(!user.getEmail().contains("@")){
            log.error("Email должен содержать @");
            throw new ValidationException("Email должен содержать @");
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

    @PutMapping
    public User updateUser(@Valid @RequestBody User user)  {
        if (users.containsKey(user.getId())) {
            users.put(user.getId(), user);
            log.info("Обновили пользователя: {}.", user);
            return user;
        } else {
            log.error("Id пользователя не найдено.");
            throw new ValidationException("Id пользователя не найдено.");
        }
    }
}
