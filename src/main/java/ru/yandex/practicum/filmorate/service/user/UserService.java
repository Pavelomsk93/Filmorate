package ru.yandex.practicum.filmorate.service.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.storage.user.UserStorage;

import java.util.List;

@Service
public class UserService {

    UserStorage userStorage;

    @Autowired
    public UserService(UserStorage userStorage){
        this.userStorage = userStorage;
    }

    public List<User> findAll(){
        return userStorage.getUser();
    }

    public User createUser(User user){
        return userStorage.createUser(user);
    }

    public User updateUser(User user){
        return userStorage.updateUser(user);
    }

    public User findById(int id){
        return userStorage.findById(id);
    }

    public void addFriend(int id,int friendId){
        userStorage.findById(id);
    }
}
