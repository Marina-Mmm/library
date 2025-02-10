package ru.itgirls.library.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import ru.itgirls.library.dto.AuthorCreateDto;
import ru.itgirls.library.dto.AuthorDto;
import ru.itgirls.library.dto.AuthorUpdateDto;
import ru.itgirls.library.dto.BookDto;
import ru.itgirls.library.model.Author;
import ru.itgirls.library.repository.AuthorRepository;

import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;

    @Override
    public AuthorDto getAuthorById(Long id) {
        log.info("Try to find author by id {}", id);
        Optional<Author> author = authorRepository.findById(id);
        if (author.isPresent()) {
            AuthorDto authorDto = convertToDto(author.get());
            log.info("Author: {}", authorDto.toString());
            return authorDto;
        } else {
            log.error("Author with id {} hadn't been found by id.", id);
            throw new NoSuchElementException("No value present.");
        }
    }

    private AuthorDto convertToDto(Author author) {
        List<BookDto> bookDtoList = author.getBooks()
                .stream()
                .map(book -> BookDto.builder()
                        .genre(book.getGenre().getName())
                        .name(book.getName())
                        .id(book.getId())
                        .build()
                ).toList();
        return AuthorDto.builder()
                .books(bookDtoList)
                .id(author.getId())
                .name(author.getName())
                .surname(author.getSurname())
                .build();
    }

    @Override
    public List<AuthorDto> getAuthorsByName(@NonNull String name) {
        log.info("Try to find author by name {}", name);
        try {
            Specification<Author> specification = (root, query, criteriaBuilder) ->
                    criteriaBuilder.equal(root.get("name"), name);

            List<Author> authors = authorRepository.findAll(specification);
            if (authors.isEmpty()) {
                log.warn("Authors with name {} hadn't been found.", name);
                return Collections.emptyList();
            }
            log.info("Authors with name {} had been found.", name);
            List<AuthorDto> authorsDto = authors.stream()
                    .map(this::convertEntityToDto)
                    .toList();
            log.info("Authors: {}", authorsDto);
            return authorsDto;
        } catch (Exception e) {
            log.error("Error occurred while searching of authors with name {}: {}", name, e.getMessage(), e);
            return Collections.emptyList();
        }
    }

    @Override
    public AuthorDto createAuthor(AuthorCreateDto authorCreateDto) {
        log.info("Create a new author.");
        try {
            Author author = authorRepository.save(convertDtoToEntity(authorCreateDto));
            AuthorDto authorDto = convertEntityToDto(author);
            log.info("The author with ID {} had been created.", author.getId());
            return authorDto;
        } catch (Exception e) {
            log.error("Error occurred while creating of the author: {}", e.getMessage());
            throw e;
        }
    }

    @Override
    public AuthorDto updateAuthor(AuthorUpdateDto authorUpdateDto) {
        log.info("Update the author with ID: {}", authorUpdateDto.getId());
        Optional<Author> authorOptional = authorRepository.findById(authorUpdateDto.getId());
        if (authorOptional.isPresent()) {
            Author author = authorOptional.get();
            author.setName(authorUpdateDto.getName());
            author.setSurname(authorUpdateDto.getSurname());
            Author savedAuthor = authorRepository.save(author);
            AuthorDto authorDto = convertEntityToDto(savedAuthor);
            log.info("Author: {} had been updated.", authorDto.toString());
            return authorDto;
        } else {
            log.error("Author with id {} hadn't been found.", authorUpdateDto.getId());
            throw new NoSuchElementException("No value present.");
        }
    }

    @Override
    public void deleteAuthor(Long id) {
        log.info("Delete the author with ID: {}", id);
        if (!authorRepository.existsById(id)) {
            log.warn("The author with ID {} hadn't been found, deletion aborted.", id);
            throw new NoSuchElementException("No value present.");
        }
        authorRepository.deleteById(id);
        log.info("The author with ID {} had been deleted", id);
    }

    @Override
    public List<AuthorDto> getAllAuthors() {
        log.info("Try to get all authors.");
        try {
            List<Author> authors = authorRepository.findAll();
            log.info("{} authors had been found.", authors.size());
            return authors.stream().map(this::convertEntityToDto).collect(Collectors.toList());
        } catch (Exception e) {
            log.error("Error occurred while searching authors: {}", e.getMessage(), e);
            return Collections.emptyList();
        }
    }

    private Author convertDtoToEntity(AuthorCreateDto authorCreateDto) {
        return Author.builder()
                .name(authorCreateDto.getName())
                .surname(authorCreateDto.getSurname())
                .build();
    }

    private AuthorDto convertEntityToDto(Author author) {
        List<BookDto> bookDtoList = null;
        if (author.getBooks() != null) {
            bookDtoList = author.getBooks()
                    .stream()
                    .map(book -> BookDto.builder()
                            .genre(book.getGenre().getName())
                            .name(book.getName())
                            .id(book.getId())
                            .build())
                    .toList();
        }
        return AuthorDto.builder()
                .id(author.getId())
                .name(author.getName())
                .surname(author.getSurname())
                .books(bookDtoList)
                .build();
    }
}