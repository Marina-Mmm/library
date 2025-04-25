package ru.itgirls.library.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.itgirls.library.dto.AuthorDto;
import ru.itgirls.library.dto.BookDto;
import ru.itgirls.library.dto.GenreDto;
import ru.itgirls.library.model.Genre;
import ru.itgirls.library.repository.GenreRepository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class GenreServiceImpl implements GenreService {

    private final GenreRepository genreRepository;

    @Override
    public GenreDto getGenreById(Long id) {
        log.info("Try to find genre by id {}", id);
        Optional<Genre> genre = genreRepository.findById(id);
        if (genre.isPresent()) {
            GenreDto genreDto = convertToDto(genre.get());
            log.info("Genre: {}", genreDto.toString());
            return genreDto;
        } else {
            log.error("Genre with id {} hadn't been found by id.", id);
            throw new NoSuchElementException("No value present.");
        }
    }

    @Override
    public GenreDto getGenresByName(String name) {
        log.info("Try to find genre by name {}", name);
        Optional<Genre> genre = genreRepository.findGenreByName(name);
        if (genre.isPresent()) {
            GenreDto genreDto = convertToDto(genre.get());
            log.info("Genre: {}", genreDto.toString());
            return genreDto;
        } else {
            log.error("Genre with id {} hadn't been found by name.", name);
            throw new NoSuchElementException("No value present.");
        }
    }

    private GenreDto convertToDto(Genre genre) {
        List<BookDto> bookDtoList = genre.getBooks()
                .stream()
                .map(book -> {
                    List<AuthorDto> authorDtoList = book.getAuthors()
                            .stream()
                            .map(author -> AuthorDto.builder()
                                    .id(author.getId())
                                    .name(author.getName())
                                    .surname(author.getSurname())
                                    .build()
                            ).toList();
                    return BookDto.builder()
                            .id(book.getId())
                            .name(book.getName())
                            .authors(authorDtoList)
                            .build();
                }).toList();
        return GenreDto.builder()
                .books(bookDtoList)
                .id(genre.getId())
                .name(genre.getName())
                .build();
    }
}