package ru.itgirls.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import ru.itgirls.library.model.UserOfLibrary;
import java.util.Optional;

public interface UserOfLibraryRepository extends JpaRepository<UserOfLibrary, Long>, JpaSpecificationExecutor<UserOfLibrary> {

    Optional<UserOfLibrary> findByUsername(String username);
}