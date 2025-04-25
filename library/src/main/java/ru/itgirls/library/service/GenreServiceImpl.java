package ru.itgirls.library.service;

import lombok.RequiredArgsConstructor;
<<<<<<< HEAD
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.itgirls.library.dto.AuthorDto;
=======
import org.springframework.stereotype.Service;
>>>>>>> master
import ru.itgirls.library.dto.BookDto;
import ru.itgirls.library.dto.GenreDto;
import ru.itgirls.library.model.Genre;
import ru.itgirls.library.repository.GenreRepository;

import java.util.List;
<<<<<<< HEAD
import java.util.NoSuchElementException;
import java.util.Optional;

@Slf4j
=======

>>>>>>> master
@Service
@RequiredArgsConstructor
public class GenreServiceImpl implements GenreService {

    private final GenreRepository genreRepository;

    @Override
<<<<<<< HEAD
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
=======
    public GenreDto getGenreById (Long id){
        Genre genre = genreRepository.findById(id).orElseThrow();
        return convertToDto(genre);
>>>>>>> master
    }

    private GenreDto convertToDto(Genre genre) {
        List<BookDto> bookDtoList = genre.getBooks()
                .stream()
<<<<<<< HEAD
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
=======
                .map(book -> BookDto.builder()
                        .genre(book.getGenre().getName())
                        .name(book.getName())
                        .id(book.getId())
                        .build()
                ).toList();
>>>>>>> master
        return GenreDto.builder()
                .books(bookDtoList)
                .id(genre.getId())
                .name(genre.getName())
                .build();
    }
<<<<<<< HEAD
}
=======
}
>>>>>>> master
