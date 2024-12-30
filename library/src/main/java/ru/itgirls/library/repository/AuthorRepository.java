package ru.itgirls.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itgirls.library.model.Author;

public interface AuthorRepository extends JpaRepository<Author, Long> {
}
