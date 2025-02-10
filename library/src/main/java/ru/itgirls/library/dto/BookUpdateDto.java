package ru.itgirls.library.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class BookUpdateDto {
    @Positive
    private Long id;
    @NotBlank(message = "You must specify the name.")
    private String name;
    @NotBlank(message = "You must specify the surname.")
    private String genre;
}