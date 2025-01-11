package ru.itgirls.library.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.itgirls.library.dto.AuthorDto;
import ru.itgirls.library.dto.BookDto;
import ru.itgirls.library.dto.GenreDto;
import ru.itgirls.library.model.Genre;
import ru.itgirls.library.repository.GenreRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GenreServiceImpl implements GenreService {

    private final GenreRepository genreRepository;

    @Override
    public GenreDto getGenreById (Long id){
        Genre genre = genreRepository.findById(id).orElseThrow();
        return convertToDto(genre);
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
