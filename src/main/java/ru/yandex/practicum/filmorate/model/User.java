package ru.yandex.practicum.filmorate.model;


import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotNull;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class User {
    //Анотации @isBlank и @Email отказываются работать
    private int id;

    @NotNull(message = "Не может быть null")
    private String email;

    @NotNull(message = "Не может быть null")
    private String login;

    @NotNull(message = "Не может быть null")
    private String name;

    @NotNull(message = "Не может быть null")
    private LocalDate birthday;
}
