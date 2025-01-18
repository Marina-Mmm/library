package ru.itgirls.library.service;

import ru.itgirls.library.dto.AuthorCreateDto;
import ru.itgirls.library.dto.AuthorDto;
import ru.itgirls.library.dto.AuthorUpdateDto;
import ru.itgirls.library.dto.BookDto;

import java.util.List;

public interface AuthorService {

    AuthorDto getAuthorById(Long id);

    List<AuthorDto> getAuthorsByName(String name);

    AuthorDto createAuthor(AuthorCreateDto authorCreateDto);

    AuthorDto updateAuthor(AuthorUpdateDto authorUpdateDto);

    void deleteAuthor(Long id);

    List<AuthorDto> getAllAuthors();
}
