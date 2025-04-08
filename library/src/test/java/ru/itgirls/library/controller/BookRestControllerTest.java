package ru.itgirls.library.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import ru.itgirls.library.dto.BookCreateDto;
import ru.itgirls.library.dto.BookDto;
import ru.itgirls.library.dto.BookUpdateDto;

import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
public class BookRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;


    @Test
    public void testGetBooksByName() throws Exception {
        String name = "Мастер и Маргарита";
        List<BookDto> books = List.of(
                BookDto.builder().id(4L).name("Мастер и Маргарита").genre("Роман").build()
        );

        mockMvc.perform(MockMvcRequestBuilders.get("/books/name")
                        .param("name", name))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(books.size()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(books.get(0).getId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value(books.get(0).getName()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].genre").value(books.get(0).getGenre()));
    }

    @Test
    public void testCreateBook() throws Exception {
        BookCreateDto bookCreateDto = new BookCreateDto("Вишневый сад", "Комедия");

        mockMvc.perform(MockMvcRequestBuilders.post("/books")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(bookCreateDto)))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Вишневый сад"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.genre").value("Комедия"));
    }

    @Test
    public void testUpdateBook() throws Exception {
        BookUpdateDto bookUpdateDto = new BookUpdateDto(26L, "Ревизор", "Комедия");

        mockMvc.perform(MockMvcRequestBuilders.put("/books")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(bookUpdateDto)))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(26L))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Ревизор"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.genre").value("Комедия"));
    }

    @Test
    public void testDeleteBook() throws Exception {
        Long id = 26L;
        BookDto bookDto = BookDto.builder()
                .id(id)
                .name("Ревизор")
                .genre("Комедия")
                .build();
        mockMvc.perform(MockMvcRequestBuilders.delete("/books/{id}", id))
                .andExpect(status().isOk());
    }
}