package ru.itgirls.library.service;

import ru.itgirls.library.dto.BookCreateDto;
import ru.itgirls.library.dto.BookDto;
import ru.itgirls.library.dto.BookUpdateDto;

import java.util.List;

public interface BookService {

    List<BookDto> getBooksByName(String name);

    BookDto createBook(BookCreateDto bookCreateDto);

    BookDto updateBook(BookUpdateDto bookUpdateDto);

    void deleteBook(Long id);

    List<BookDto> getAllBooks();
}