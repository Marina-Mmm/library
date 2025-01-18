package ru.itgirls.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itgirls.library.model.Book;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> findBooksByName(String name);
}
