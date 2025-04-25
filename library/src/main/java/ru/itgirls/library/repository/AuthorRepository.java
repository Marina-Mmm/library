package ru.itgirls.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
<<<<<<< HEAD
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import ru.itgirls.library.model.Author;

public interface AuthorRepository extends JpaRepository<Author, Long>, JpaSpecificationExecutor<Author> {

}
=======
import ru.itgirls.library.model.Author;

public interface AuthorRepository extends JpaRepository<Author, Long> {
}
>>>>>>> master
