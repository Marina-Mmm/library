package ru.itgirls.library.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.itgirls.library.dto.BookDto;
import ru.itgirls.library.model.Book;
import ru.itgirls.library.repository.BookRepository;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService{

    private final BookRepository bookRepository;
    @Override
    public List<BookDto> getBooksByName(String name) {
        List<Book> books = bookRepository.findBooksByName(name);
        if (books.isEmpty()){
            return Collections.emptyList();
        }
        return books.stream()
                .map(this::convertEntityToDto)
                .toList();
    }

    private BookDto convertEntityToDto(Book book) {
        return BookDto.builder()
                .id(book.getId())
                .genre(book.getGenre().getName())
                .name(book.getName())
                .build();
    }
}
