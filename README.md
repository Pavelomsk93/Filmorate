# java-filmorate
Template repository for Filmorate project.

# Сервис по работе с фильмами #

### Данные будут храниться в БД. Схема БД представлена ниже. ###


![Схема Базы данных](C:\Users\pavel\dev\java-filmorate\src\main\resources\drawSQL-export-2022-08-30_11_31.png)

### Примеры Endpoint запросов (программа написана на Java): ###

```  
@PostMapping
    public User createUser(@Valid @RequestBody User user){
           return userService.createUser(user);
    }
```

```  
 @GetMapping("/popular")
    public List<Film> popularFilms(@RequestParam(value = "count", defaultValue = "10", required = false) int count ){
        return filmService.popularFilms(count);
    }
```