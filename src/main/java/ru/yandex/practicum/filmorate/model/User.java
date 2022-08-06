package ru.yandex.practicum.filmorate.model;


import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class User {

    private int id;

    @Email
    private String email;

    @NotNull(message = "Не может быть null")
    @Pattern(regexp = "^\\S*$")
    private String login;

    @NotNull(message = "Не может быть null")
    private String name;

    @NotNull(message = "Не может быть null")
    private LocalDate birthday;
}
