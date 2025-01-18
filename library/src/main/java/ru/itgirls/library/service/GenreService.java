package ru.itgirls.library.service;

import ru.itgirls.library.dto.GenreDto;

public interface GenreService {

    GenreDto getGenreById(Long id);

    GenreDto getGenresByName(String name);
}
