package ru.itgirls.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.itgirls.library.model.Author;

import java.util.List;

public interface AuthorRepository extends JpaRepository<Author, Long> {

    @Query(nativeQuery = true, value = "Select * from author where name = ?")
    List<Author> findAuthorsByNameBySQL (String name);
}
