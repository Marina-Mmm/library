package ru.itgirls.library.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import ru.itgirls.library.dto.AuthorCreateDto;
import ru.itgirls.library.dto.AuthorDto;
import ru.itgirls.library.dto.AuthorUpdateDto;
import ru.itgirls.library.service.AuthorService;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
public class AuthorRestControllerTest { @Autowired
private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private AuthorService authorService;

    @Test
    public void testGetAuthorById() throws Exception {
        Long id = 1L;
        AuthorDto authorDto = AuthorDto.builder()
                .id(id)
                .name("Александр")
                .surname("Пушкин")
                .build();
        when(authorService.getAuthorById(id)).thenReturn(authorDto);
        mockMvc.perform(MockMvcRequestBuilders.get("/authors/{id}", id))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(authorDto.getId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value(authorDto.getName()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.surname").value(authorDto.getSurname()));
        verify(authorService, times(1)).getAuthorById(id);
    }

    @Test
    public void testGetAuthorsByName() throws Exception {
        String name = "Михаил";
        List<AuthorDto> authors = List.of(
                AuthorDto.builder().id(4L).name("Михаил").surname("Булгаков").build(),
                AuthorDto.builder().id(26L).name("Михаил").surname("Лыньков").build()
        );

        when(authorService.getAuthorsByName(name)).thenReturn(authors);
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
        verify(authorService, times(1)).getAuthorsByName(name);
    }

    @Test
    public void testCreateAuthor() throws Exception {
        AuthorCreateDto authorCreateDto = new AuthorCreateDto("Василий", "Быков");
        AuthorDto authorDto = new AuthorDto();
        authorDto.setName("Василий");
        authorDto.setSurname("Быков");

        when(authorService.createAuthor(authorCreateDto)).thenReturn(authorDto);
        mockMvc.perform(MockMvcRequestBuilders.post("/authors")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(authorCreateDto)))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Василий"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.surname").value("Быков"));
        verify(authorService, times(1)).createAuthor(any(AuthorCreateDto.class));
    }

    @Test
    public void testUpdateAuthor() throws Exception {
        AuthorUpdateDto authorUpdateDto = new AuthorUpdateDto(15L, "Светлана", "Алексиевич");
        AuthorDto authorDto = new AuthorDto();
        authorDto.setId(15L);
        authorDto.setName("Светлана");
        authorDto.setSurname("Алексиевич");

        when(authorService.updateAuthor(authorUpdateDto)).thenReturn(authorDto);
        mockMvc.perform(MockMvcRequestBuilders.put("/authors")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(authorUpdateDto)))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(15L))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Светлана"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.surname").value("Алексиевич"));
        verify(authorService, times(1)).updateAuthor(any(AuthorUpdateDto.class));
    }

    @Test
    public void testDeleteAuthor() throws Exception {
        Long id = 15L;

        mockMvc.perform(MockMvcRequestBuilders.delete("/authors/{id}", id))
                .andExpect(status().isOk());
        verify(authorService, times(1)).deleteAuthor(id);
    }
}