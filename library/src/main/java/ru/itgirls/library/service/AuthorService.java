package ru.itgirls.library.service;

import ru.itgirls.library.dto.AuthorDto;
import ru.itgirls.library.dto.BookDto;
import ru.itgirls.library.model.Author;

import java.util.List;

public interface AuthorService {

    AuthorDto getAuthorById(Long id);
    List<AuthorDto> getAuthorsByNameBySQL(String name);
}
