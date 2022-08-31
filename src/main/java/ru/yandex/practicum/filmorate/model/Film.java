package ru.yandex.practicum.filmorate.model;

import lombok.*;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Film {

    private int id;

    @NotNull(message = "Не может быть null")
    private String name;

    @NotNull(message = "Не может быть null")
    private String description;

    @NotNull (message = "Не может быть null")
    private LocalDate releaseDate;

    @NotNull
    private Integer duration;


    private  Set<Integer> likes = new HashSet<>() ;


}
