package ru.itgirls.library.controller.rest;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.itgirls.library.dto.GenreDto;
import ru.itgirls.library.service.GenreService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/genres")
public class GenreRestController {

    private final GenreService genreService;

    @GetMapping("/{id}")
    GenreDto getGenreById(@PathVariable("id") Long id) {
        return genreService.getGenreById(id);
    }

    @GetMapping("/name")
    GenreDto getGenresByName(@RequestParam("name") String name) {
        return genreService.getGenresByName(name);
    }
}