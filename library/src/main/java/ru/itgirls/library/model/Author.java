package ru.itgirls.library.model;

import jakarta.persistence.*;
<<<<<<< HEAD
import lombok.*;
=======
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

>>>>>>> master
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
<<<<<<< HEAD
@Setter
=======
>>>>>>> master
@Builder
@Entity
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String surname;

    @ManyToMany(mappedBy = "authors")
    private Set<Book> books;
<<<<<<< HEAD
}
=======
}
>>>>>>> master
