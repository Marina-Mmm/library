package ru.itgirls.library.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.itgirls.library.dto.AuthorDto;
import ru.itgirls.library.dto.BookCreateDto;
import ru.itgirls.library.dto.BookDto;
import ru.itgirls.library.dto.BookUpdateDto;
import ru.itgirls.library.model.Book;
import ru.itgirls.library.model.Genre;
import ru.itgirls.library.repository.BookRepository;
import ru.itgirls.library.repository.GenreRepository;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final GenreRepository genreRepository;

    @Override
    public List<BookDto> getBooksByName(String name) {
        log.info("Try to find book by name {}", name);
        try {
            List<Book> books = bookRepository.findBooksByName(name);
            if (books.isEmpty()) {
                log.warn("Books with name {} hadn't been found.", name);
                return Collections.emptyList();
            }
            log.info("Books with name {} had been found.", name);
            List<BookDto> booksDto = books.stream()
                    .map(this::convertEntityToDto)
                    .toList();
            log.info("Books: {}", booksDto);
            return booksDto;
        } catch (Exception e) {
            log.error("Error occurred while searching of books with name {}: {}", name, e.getMessage(), e);
            return Collections.emptyList();
        }
    }

    @Override
    public BookDto createBook(BookCreateDto bookCreateDto) {
        log.info("Create a new book.");
        try {
            Book book = bookRepository.save(convertDtoToEntity(bookCreateDto));
            BookDto bookDto = convertEntityToDto(book);
            log.info("The book with ID {} had been created.", book.getId());
            return bookDto;
        } catch (Exception e) {
            log.error("Error occurred while creating of the book: {}", e.getMessage());
            throw e;
        }
    }

    @Override
    public BookDto updateBook(BookUpdateDto bookUpdateDto) {
        log.info("Update the book with ID: {}", bookUpdateDto.getId());
        Genre genre = genreRepository.findGenreByName(bookUpdateDto.getGenre()).orElseThrow();
        Optional<Book> bookOptional = bookRepository.findById(bookUpdateDto.getId());
        if (bookOptional.isPresent()) {
            Book book = bookOptional.get();
            book.setName(bookUpdateDto.getName());
            book.setGenre(genre);
            Book savedBook = bookRepository.save(book);
            BookDto bookDto = convertEntityToDto(savedBook);
            log.info("Book: {} had been updated.", bookDto.toString());
            return bookDto;
        } else {
            log.error("Book with id {} hadn't been found.", bookUpdateDto.getId());
            throw new NoSuchElementException("No value present.");
        }
    }

    @Override
    public void deleteBook(Long id) {
        log.info("Delete the book with ID: {}", id);
        if (!bookRepository.existsById(id)) {
            log.warn("The book with ID {} hadn't been found, deletion aborted.", id);
            throw new NoSuchElementException("No value present.");
        }
        bookRepository.deleteById(id);
        log.info("The book with ID {} had been deleted", id);
    }

    @Override
    public List<BookDto> getAllBooks() {
        log.info("Try to get all books.");
        try {
            List<Book> books = bookRepository.findAll();
            log.info("{} books had been found.", books.size());
            return books.stream().map(this::convertEntityToDto).collect(Collectors.toList());
        } catch (Exception e) {
            log.error("Error occurred while searching books: {}", e.getMessage(), e);
            return Collections.emptyList();
        }
    }

    private Book convertDtoToEntity(BookCreateDto bookCreateDto) {
        Genre genre = genreRepository.findGenreByName(bookCreateDto.getGenre()).orElseThrow();
        return Book.builder()
                .name(bookCreateDto.getName())
                .genre(genre)
                .build();
    }

    private BookDto convertEntityToDto(Book book) {
        return BookDto.builder()
                .id(book.getId())
                .genre(book.getGenre().getName())
                .name(book.getName())
                .build();
    }
}