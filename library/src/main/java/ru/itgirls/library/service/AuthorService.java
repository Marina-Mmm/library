package ru.itgirls.library.service;

import ru.itgirls.library.dto.AuthorDto;

public interface AuthorService {

    AuthorDto getAuthorById(Long id);
}
