package ru.itgirls.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itgirls.library.model.Genre;

import java.util.Optional;

public interface GenreRepository extends JpaRepository<Genre, Long> {

    Optional<Genre> findGenreByName(String name);
}
