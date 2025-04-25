package ru.itgirls.library.dto;


<<<<<<< HEAD
import com.fasterxml.jackson.annotation.JsonInclude;
=======
>>>>>>> master
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
<<<<<<< HEAD
=======

>>>>>>> master
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
<<<<<<< HEAD
@JsonInclude(JsonInclude.Include.NON_NULL)
=======
>>>>>>> master
public class AuthorDto {
    private Long id;
    private String name;
    private String surname;
<<<<<<< HEAD
    private List<BookDto> books;
}
=======

    private List<BookDto> books;
}
>>>>>>> master
