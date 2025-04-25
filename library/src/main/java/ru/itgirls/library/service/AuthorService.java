package ru.itgirls.library.service;

<<<<<<< HEAD
import ru.itgirls.library.dto.AuthorCreateDto;
import ru.itgirls.library.dto.AuthorDto;
import ru.itgirls.library.dto.AuthorUpdateDto;
import java.util.List;
=======
import ru.itgirls.library.dto.AuthorDto;
>>>>>>> master

public interface AuthorService {

    AuthorDto getAuthorById(Long id);
<<<<<<< HEAD

    List<AuthorDto> getAuthorsByName(String name);

    AuthorDto createAuthor(AuthorCreateDto authorCreateDto);

    AuthorDto updateAuthor(AuthorUpdateDto authorUpdateDto);

    void deleteAuthor(Long id);

    List<AuthorDto> getAllAuthors();
}
=======
}
>>>>>>> master
