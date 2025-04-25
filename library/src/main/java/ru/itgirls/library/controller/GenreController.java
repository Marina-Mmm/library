package ru.itgirls.library.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ru.itgirls.library.dto.GenreDto;
import ru.itgirls.library.service.GenreService;

@RestController
@RequiredArgsConstructor
public class GenreController {

    private final GenreService genreService;

    @GetMapping("/genres/{id}")
    GenreDto getGenreById(@PathVariable("id") Long id){
        return genreService.getGenreById(id);
    }
}
