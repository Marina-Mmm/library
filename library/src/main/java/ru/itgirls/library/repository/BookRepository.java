package ru.itgirls.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itgirls.library.model.Book;
<<<<<<< HEAD
import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> findBooksByName(String name);
}
=======

public interface BookRepository extends JpaRepository<Book,Long> {
}
>>>>>>> master
