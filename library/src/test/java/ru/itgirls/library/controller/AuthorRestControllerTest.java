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
import ru.itgirls.library.dto.AuthorCreateDto;
import ru.itgirls.library.dto.AuthorDto;
import ru.itgirls.library.dto.AuthorUpdateDto;

import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
public class AuthorRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testGetAuthorById() throws Exception {
        Long id = 1L;
        AuthorDto authorDto = AuthorDto.builder()
                .id(id)
                .name("Александр")
                .surname("Пушкин")
                .build();

        mockMvc.perform(MockMvcRequestBuilders.get("/authors/{id}", id))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(authorDto.getId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value(authorDto.getName()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.surname").value(authorDto.getSurname()));
    }

    @Test
    public void testGetAuthorsByName() throws Exception {
        String name = "Михаил";
        List<AuthorDto> authors = List.of(
                AuthorDto.builder().id(4L).name("Михаил").surname("Булгаков").build(),
                AuthorDto.builder().id(26L).name("Михаил").surname("Лыньков").build()
        );

        mockMvc.perform(MockMvcRequestBuilders.get("/authors/name")
                        .param("name", name))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(authors.size()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(authors.get(0).getId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value(authors.get(0).getName()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].surname").value(authors.get(0).getSurname()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].id").value(authors.get(1).getId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].name").value(authors.get(1).getName()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].surname").value(authors.get(1).getSurname()));
    }

    @Test
    public void testCreateAuthor() throws Exception {
        AuthorCreateDto authorCreateDto = new AuthorCreateDto("Василий", "Быков");

        mockMvc.perform(MockMvcRequestBuilders.post("/authors")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(authorCreateDto)))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Василий"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.surname").value("Быков"));
    }

    @Test
    public void testUpdateAuthor() throws Exception {
        AuthorUpdateDto authorUpdateDto = new AuthorUpdateDto(30L, "Светлана", "Алексиевич");

        mockMvc.perform(MockMvcRequestBuilders.put("/authors")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(authorUpdateDto)))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(30L))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Светлана"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.surname").value("Алексиевич"));
    }

    @Test
    public void testDeleteAuthor() throws Exception {
        Long id = 30L;
        AuthorDto authorDto = AuthorDto.builder()
                .id(id)
                .name("Светлана")
                .surname("Алексиевич")
                .build();
        mockMvc.perform(MockMvcRequestBuilders.delete("/authors/{id}", id))
                .andExpect(status().isOk());
    }
}