package ru.yandex.practicum.filmorate.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import org.jetbrains.annotations.NotNull;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PastOrPresent;
import java.time.LocalDate;

@Data
@AllArgsConstructor
public class User {

    private int id;

    @NotNull
    @NotBlank(message = "Email не может быть пустым.")
    @Email(message = "Email должен быть корректным адресом электронной почты.")
    private String email;

    @NotNull
    @NotBlank(message = "Логин не может быть пустым.")
    private String login;

    @NotNull
    private String name;

    @NotNull
    @PastOrPresent(message = "Дата рождения не может быть в будущем")
    private LocalDate birthday;

}
