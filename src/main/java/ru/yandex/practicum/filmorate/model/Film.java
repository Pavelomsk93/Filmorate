package ru.yandex.practicum.filmorate.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.jetbrains.annotations.NotNull;


import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@Data
@AllArgsConstructor
public class Film {

    private int id;

    @NotNull
    @NotBlank
    private String name;

    @NotNull
    @NotBlank
    private String description;

    @NotNull
    private LocalDate releaseDate;

   @NotNull
    private long duration;
}
