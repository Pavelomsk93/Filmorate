package ru.yandex.practicum.filmorate.service.user;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.exceptions.UserNotFoundException;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.storage.user.UserStorage;

import java.util.ArrayList;
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

    public void addFriend(int id, int friendId){
        if(!userStorage.getUser().contains(userStorage.findById(id))||!userStorage.getUser().contains(userStorage.findById(friendId))){
            log.error("Пользователь не найден");
            throw new UserNotFoundException("Пользователь не найден");
        }else{
            userStorage.findById(id).getFriends().add(friendId);
            userStorage.findById(friendId).getFriends().add(id);
            log.info("Пользователь {} добавил в друзья пользователя {}.",userStorage.findById(id).getLogin(),userStorage.findById(friendId).getLogin());
        }

    }

    public void removeFriend(int id, int friendId){
       if(userStorage.findById(id).getFriends().contains(friendId)){
           log.info("Пользователь {} удалил из друзей пользователя {}.",userStorage.findById(id).getLogin(),userStorage.findById(friendId).getLogin());
           userStorage.findById(id).getFriends().remove(friendId);
       }else{
           log.error("Такого пользователя нет в друзьях");
           throw new UserNotFoundException("Такого пользователя нет в друзьях");
       }
    }

    public List<User> getAllFriends(int id){
        List<User> friends = new ArrayList<>();
        for(Integer user:userStorage.findById(id).getFriends()){
            friends.add(userStorage.findById(user));
        }
        return friends;
    }

    public List<User> getCommonFriends(int id,int otherId){
        Set<Integer> common = new HashSet<>(userStorage.findById(otherId).getFriends());
        common.retainAll(userStorage.findById(id).getFriends());
        List<User> commonFriends = new ArrayList<>();
        for(Integer user : common){
            commonFriends.add(userStorage.findById(user));
        }
        return commonFriends;
    }
}
