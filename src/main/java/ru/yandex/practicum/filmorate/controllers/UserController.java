package ru.yandex.practicum.filmorate.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.service.user.UserService;


import javax.validation.Valid;
import java.util.List;



@RestController
@Slf4j
@RequestMapping("/users")
@Validated
public class UserController {

    UserService userService;
    @Autowired
    public UserController(UserService userService){
        this.userService=userService;
    }
    @GetMapping
    public List<User> getUser() {
        return userService.findAll();
    }

    @PostMapping
    public User createUser(@Valid @RequestBody User user){
           return userService.createUser(user);
    }

    @PutMapping
    public User updateUser(@Valid @RequestBody User user)  {
       return userService.updateUser(user);
    }

    @GetMapping("/{id}")
    public User findBId(@PathVariable int id){
        return userService.findById(id);
    }
}
