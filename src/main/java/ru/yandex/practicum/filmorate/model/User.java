package ru.yandex.practicum.filmorate.model;


import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class User {

    private Set<Integer> friends;


    private Integer id;

    @Email
    private String email;

    @NotNull(message = "Не может быть null")
    @Pattern(regexp = "^\\S*$")
    private String login;

    @NotNull(message = "Не может быть null")
    private String name;

    @NotNull(message = "Не может быть null")
    private LocalDate birthday;

    public User(Integer id,String email,String login,String name,LocalDate birthday){
        this.id = id;
        this.email = email;
        this.login = login;
        this.name = name;
        this.birthday = birthday;
    }
}
