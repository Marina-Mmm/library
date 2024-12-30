package ru.itgirls.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itgirls.library.model.Book;

public interface BookRepository extends JpaRepository<Book,Long> {
}
