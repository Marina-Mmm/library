package ru.itgirls.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itgirls.library.model.Genre;

public interface GenreRepository extends JpaRepository<Genre,Long> {
}
