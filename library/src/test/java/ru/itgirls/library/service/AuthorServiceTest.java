package ru.itgirls.library.service;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.domain.Specification;
import ru.itgirls.library.dto.AuthorCreateDto;
import ru.itgirls.library.dto.AuthorDto;
import ru.itgirls.library.dto.AuthorUpdateDto;
import ru.itgirls.library.model.Author;
import ru.itgirls.library.model.Book;
import ru.itgirls.library.repository.AuthorRepository;

import java.util.*;

import static org.mockito.Mockito.*;

@SpringBootTest
public class AuthorServiceTest {

    @Mock
    AuthorRepository authorRepository;

    @InjectMocks
    AuthorServiceImpl authorService;

    @Test
    public void testGetAuthorById() {
        Long id = 1L;
        String name = "Lo";
        String surname = "Garry";
        Set<Book> books = new HashSet<>();
        Author author = new Author(id, name, surname, books);

        when(authorRepository.findById(id)).thenReturn(Optional.of(author));

        AuthorDto authorDto = authorService.getAuthorById(id);
        verify(authorRepository).findById(id);
        Assertions.assertEquals(authorDto.getId(), author.getId());
        Assertions.assertEquals(authorDto.getName(), author.getName());
        Assertions.assertEquals(authorDto.getSurname(), author.getSurname());
    }

    @Test
    public void testGetAuthorByIdFailed() {
        Long id = 1L;

        when(authorRepository.findById(id)).thenReturn(Optional.empty());

        Assertions.assertThrows(NoSuchElementException.class, () -> authorService.getAuthorById(id));
        verify(authorRepository).findById(id);
    }

    @Test
    public void testGetAuthorByName() {
        String name = "Li";
        Author author1 = new Author(1L, "Li", "Smith", new HashSet<>());
        Author author2 = new Author(2L, "Li", "Paris", new HashSet<>());
        List<Author> authors = List.of(author1, author2);

        when(authorRepository.findAll(any(Specification.class))).thenReturn(authors);

        List<AuthorDto> authorDtoList = authorService.getAuthorsByName(name);
        verify(authorRepository).findAll(any(Specification.class));
        Assertions.assertEquals(2, authorDtoList.size());
        Assertions.assertEquals("Li", authorDtoList.get(0).getName());
        Assertions.assertEquals("Li", authorDtoList.get(1).getName());
    }

    @Test
    public void testGetAuthorByNameFailed() {
        String name = "Li";

        when(authorRepository.findAll(any(Specification.class))).thenReturn(Collections.emptyList());

        List<AuthorDto> authorDtoList = authorService.getAuthorsByName(name);
        Assertions.assertTrue(authorDtoList.isEmpty());
        verify(authorRepository).findAll(any(Specification.class));
    }

    @Test
    public void testCreateAuthor() {
        AuthorCreateDto authorCreateDto = new AuthorCreateDto("Ted", "Large");
        Author author = new Author(1L, "Ted", "Large", new HashSet<>());
        AuthorCreateDto expectedAuthorDto = new AuthorCreateDto("Ted", "Large");

        when(authorRepository.save(any(Author.class))).thenReturn(author);

        AuthorDto authorDto = authorService.createAuthor(authorCreateDto);
        Assertions.assertEquals(expectedAuthorDto.getName(), authorDto.getName());
        Assertions.assertEquals(expectedAuthorDto.getSurname(), authorDto.getSurname());
        verify(authorRepository).save(any(Author.class));
    }

    @Test
    public void testCreateAuthorFailed() {
        AuthorCreateDto authorCreateDto = new AuthorCreateDto("Ted", "Large");

        when(authorRepository.save(any(Author.class))).thenThrow(new RuntimeException("Save failed"));

        Assertions.assertThrows(RuntimeException.class, () -> authorService.createAuthor(authorCreateDto));
        verify(authorRepository).save(any(Author.class));
    }

    @Test
    public void testUpdateAuthor() {
        AuthorUpdateDto authorUpdateDto = new AuthorUpdateDto(1L, "Updated Name", "Updated Surname");
        Author author = new Author(1L, "Old Name", "Old Surname", new HashSet<>());
        Author updatedAuthor = new Author(1L, "Updated Name", "Updated Surname", new HashSet<>());

        when(authorRepository.findById(1L)).thenReturn(Optional.of(author));
        when(authorRepository.save(any(Author.class))).thenReturn(updatedAuthor);

        AuthorDto authorDto = authorService.updateAuthor(authorUpdateDto);
        Assertions.assertEquals("Updated Name", authorDto.getName());
        Assertions.assertEquals("Updated Surname", authorDto.getSurname());
        verify(authorRepository).save(any(Author.class));
    }

    @Test
    public void testUpdateAuthorFailed() {
        AuthorUpdateDto authorUpdateDto = new AuthorUpdateDto(1L, "Updated Name", "Updated Surname");

        when(authorRepository.findById(1L)).thenReturn(Optional.empty());

        Assertions.assertThrows(RuntimeException.class, () -> authorService.updateAuthor(authorUpdateDto));
        verify(authorRepository).findById(1L);
    }

    @Test
    public void testDeleteAuthor() {
        Long authorId = 1L;

        when(authorRepository.existsById(authorId)).thenReturn(true);

        authorService.deleteAuthor(authorId);
        verify(authorRepository).deleteById(authorId);
    }

    @Test
    public void testDeleteAuthorFailed() {
        Long authorId = 1L;

        when(authorRepository.existsById(authorId)).thenReturn(false);

        Assertions.assertThrows(NoSuchElementException.class, () -> authorService.deleteAuthor(authorId));
        verify(authorRepository).existsById(authorId);
    }
}