package ru.itgirls.library.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
<<<<<<< HEAD
=======

>>>>>>> master
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@Entity
public class Genre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

<<<<<<< HEAD
    @OneToMany(mappedBy = "genre", fetch = FetchType.LAZY)
    private Set<Book> books;
}
=======
    @OneToMany (mappedBy="genre", fetch=FetchType.EAGER)
    private Set<Book> books;
}
>>>>>>> master
