package ru.itgirls.library.service;

import ru.itgirls.library.dto.BookDto;

import java.util.List;

public interface BookService {

    List<BookDto> getBooksByName(String name);
}
