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
    public List<User> getUsers() {
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

    @DeleteMapping
    public void removeUser(@Valid @RequestBody User user){
        userService.removeUser(user);
    }

    @PutMapping("/{id}/friends/{friendId}")
    public void addFriends(@PathVariable int id, @PathVariable int friendId){
         userService.addFriend(id,friendId);
    }

    @DeleteMapping("/{id}/friends/{friendId}")
    public void deleteFriends(@PathVariable int id, @PathVariable int friendId){
        userService.removeFriend(id,friendId);
    }

    @GetMapping("/{id}/friends")
    public List<User> getAllFriends(@PathVariable int id){
        return userService.getAllFriends(id);
    }

    @GetMapping("/{id}/friends/common/{otherId}")
    public List<User> getCommonFriends(@PathVariable int id,@PathVariable int otherId){
        return userService.getCommonFriends(id,otherId);
    }


}
