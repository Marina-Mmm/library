package ru.itgirls.library.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import ru.itgirls.library.dto.BookCreateDto;
import ru.itgirls.library.dto.BookDto;
import ru.itgirls.library.dto.BookUpdateDto;
import ru.itgirls.library.model.Book;
import ru.itgirls.library.model.Genre;
import ru.itgirls.library.repository.BookRepository;
import ru.itgirls.library.repository.GenreRepository;

import java.util.*;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
public class BookServiceTest {

    @Mock
    BookRepository bookRepository;

    @Mock
    private GenreRepository genreRepository;

    @InjectMocks
    BookServiceImpl bookService;

    @Test
    public void testGetBookByName() {
        String name = "Детство";
        Genre genre1 = new Genre(1L, "Повесть", new HashSet<>());
        Genre genre2 = new Genre(1L, "Автобиографическая повесть", new HashSet<>());
        Book book1 = new Book(1L, "Детство", genre1, new HashSet<>());
        Book book2 = new Book(2L, "Детство", genre2, new HashSet<>());
        List<Book> books = List.of(book1, book2);

        when(bookRepository.findBooksByName(name)).thenReturn(books);

        List<BookDto> bookDtoList = bookService.getBooksByName(name);
        verify(bookRepository).findBooksByName(name);
        Assertions.assertEquals(2, bookDtoList.size());
        Assertions.assertEquals("Детство", bookDtoList.get(0).getName());
        Assertions.assertEquals("Детство", bookDtoList.get(1).getName());
    }

    @Test
    public void testGetBookByNameFailed() {
        String name = "Детство";

        when(bookRepository.findBooksByName(name)).thenReturn(Collections.emptyList());

        List<BookDto> bookDtoList = bookService.getBooksByName(name);
        Assertions.assertTrue(bookDtoList.isEmpty());
        verify(bookRepository).findBooksByName(name);
    }

    @Test
    public void testCreateBook() {
        BookCreateDto bookCreateDto = new BookCreateDto("Юность", "Повесть");
        Genre genre = new Genre(1L, "Повесть", new HashSet<>());
        Book book = new Book(1L, "Юность", genre, new HashSet<>());
        BookCreateDto expectedBookDto = new BookCreateDto("Юность", "Повесть");

        when(genreRepository.findGenreByName(bookCreateDto.getGenre())).thenReturn(Optional.of(genre));
        when(bookRepository.save(any(Book.class))).thenReturn(book);

        BookDto bookDto = bookService.createBook(bookCreateDto);
        Assertions.assertEquals(expectedBookDto.getName(), bookDto.getName());
        Assertions.assertEquals(expectedBookDto.getGenre(), bookDto.getGenre());
        verify(bookRepository).save(any(Book.class));
    }

    @Test
    public void testCreateBookFailed() {
        BookCreateDto bookCreateDto = new BookCreateDto("Юность", "Повесть");
        Genre genre = new Genre(1L, "Повесть", new HashSet<>());

        when(genreRepository.findGenreByName(bookCreateDto.getGenre())).thenReturn(Optional.of(genre));
        when(bookRepository.save(any(Book.class))).thenThrow(new RuntimeException("Save failed"));

        Assertions.assertThrows(RuntimeException.class, () -> bookService.createBook(bookCreateDto));
        verify(bookRepository).save(any(Book.class));
    }

    @Test
    public void testUpdateBook() {
        BookUpdateDto bookUpdateDto = new BookUpdateDto(1L, "Updated Name", "Updated Genre");
        Genre genre = new Genre(1L, "Updated Genre", new HashSet<>());
        Book existingBook = new Book(1L, "Old Name", genre, new HashSet<>());
        Book savedBook = new Book(1L, "Updated Name", genre, new HashSet<>());

        when(genreRepository.findGenreByName(bookUpdateDto.getGenre())).thenReturn(Optional.of(genre));
        when(bookRepository.findById(bookUpdateDto.getId())).thenReturn(Optional.of(existingBook));
        when(bookRepository.save(any(Book.class))).thenReturn(savedBook);

        BookDto bookDto = bookService.updateBook(bookUpdateDto);
        Assertions.assertEquals("Updated Name", bookDto.getName());
        Assertions.assertEquals("Updated Genre", bookDto.getGenre());
        verify(bookRepository).save(any(Book.class));
    }

    @Test
    public void testUpdateBookFailed() {
        BookUpdateDto bookUpdateDto = new BookUpdateDto(1L, "Updated Name", "Updated Genre");
        Genre genre = new Genre(1L, "Updated Genre", new HashSet<>());

        when(genreRepository.findGenreByName(bookUpdateDto.getGenre())).thenReturn(Optional.of(genre));
        when(bookRepository.findById(1L)).thenReturn(Optional.empty());

        Assertions.assertThrows(RuntimeException.class, () -> bookService.updateBook(bookUpdateDto));
        verify(bookRepository).findById(1L);
    }

    @Test
    public void testDeleteBook() {
        Long bookId = 1L;

        when(bookRepository.existsById(bookId)).thenReturn(true);

        bookService.deleteBook(bookId);
        verify(bookRepository).deleteById(bookId);
    }

    @Test
    public void testDeleteBookFailed() {
        Long authorId = 1L;

        when(bookRepository.existsById(authorId)).thenReturn(false);

        Assertions.assertThrows(NoSuchElementException.class, () -> bookService.deleteBook(authorId));
        verify(bookRepository).existsById(authorId);
    }
}