package ru.yandex.practicum.filmorate.service.user;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.exceptions.UserNotFoundException;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.storage.user.UserStorage;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Slf4j
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

    public Set<Integer> addFriend(int id, int friendId){
        userStorage.findById(id).getFriends().add(friendId);
        userStorage.findById(friendId).getFriends().add(id);
        return userStorage.findById(id).getFriends();
    }

    public Set<Integer> removeFriend(int id, int friendId){
       if(userStorage.findById(id).getFriends().contains(friendId)){
           userStorage.findById(id).getFriends().remove(friendId);
       }else{
           log.error("Такого пользователя нет в друзьях");
           throw new UserNotFoundException("Такого пользователя нет в друзьях");
       }
       return userStorage.findById(id).getFriends();
    }

    public Set<Integer> getAllFriends(int id){
        return userStorage.findById(id).getFriends();
    }

    public Set<Integer> commonFriends(int id,int otherId){
        Set<Integer> common = new HashSet<>(userStorage.findById(otherId).getFriends());
        common.retainAll(userStorage.findById(id).getFriends());
        return common;
    }
}
