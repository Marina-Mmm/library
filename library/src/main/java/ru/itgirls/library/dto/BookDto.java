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
import java.util.List;
=======
>>>>>>> master

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
<<<<<<< HEAD
@JsonInclude(JsonInclude.Include.NON_NULL)
=======
>>>>>>> master
public class BookDto {
    private Long id;
    private String name;
    private String genre;
<<<<<<< HEAD
    private List<AuthorDto> authors;
}
=======
}
>>>>>>> master
