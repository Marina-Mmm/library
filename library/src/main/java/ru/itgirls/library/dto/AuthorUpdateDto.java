package ru.itgirls.library.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class AuthorUpdateDto {
    @Positive
    private Long id;
    @Size(min = 2, max = 10)
    @NotBlank(message = "You must specify the name.")
    private String name;
    @NotBlank(message = "You must specify the surname.")
    private String surname;
}