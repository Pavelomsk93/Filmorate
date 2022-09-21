package ru.yandex.practicum.filmorate.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.model.Mpa;
import ru.yandex.practicum.filmorate.storage.mpa.MpaDaoStorage;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/mpa")
public class MpaController {

    private final MpaDaoStorage mpaDaoStorage;

    @GetMapping("/{id}")
    public Mpa findById(@PathVariable Integer id) {
        return mpaDaoStorage.getMpaById(id);
    }

    @GetMapping
    public List<Mpa> findAll() {
        return mpaDaoStorage.getAllMpa();
    }
}