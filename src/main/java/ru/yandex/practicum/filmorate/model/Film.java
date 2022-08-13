package ru.yandex.practicum.filmorate.model;

import lombok.*;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Film {

   private Set<Integer> likes ;

    @NotNull
    private Integer id;

    @NotNull(message = "Не может быть null")
    private String name;

    @NotNull(message = "Не может быть null")
    private String description;

    @NotNull (message = "Не может быть null")
    private LocalDate releaseDate;

    @NotNull
    private Integer duration;

    public Film(Integer id,String name,String description,LocalDate releaseDate,Integer duration){
        this.id = id;
        this.name = name;
        this.description = description;
        this.releaseDate = releaseDate;
        this.duration = duration;
    }
}
