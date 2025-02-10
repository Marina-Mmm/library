package ru.itgirls.library.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class BookCreateDto {
    @NotBlank(message = "You must specify the name.")
    private String name;
    @NotBlank(message = "You must specify the genre.")
    private String genre;
}