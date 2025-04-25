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
public class GenreDto {
    private Long id;
    private String name;

    private List<BookDto> books;
<<<<<<< HEAD
}
=======
}
>>>>>>> master
