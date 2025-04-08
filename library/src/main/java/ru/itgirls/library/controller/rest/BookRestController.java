package ru.itgirls.library.controller.rest;


import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.itgirls.library.dto.BookCreateDto;
import ru.itgirls.library.dto.BookDto;
import ru.itgirls.library.dto.BookUpdateDto;
import ru.itgirls.library.service.BookService;
import java.util.List;

@RestController
@RequiredArgsConstructor
@SecurityRequirement(name = "library-users")
@RequestMapping("/books")
public class BookRestController {

    private final BookService bookService;

    @GetMapping("/name")
    List<BookDto> getBooksByName(@RequestParam("name") String name) {
        return bookService.getBooksByName(name);
    }

    @PostMapping()
    BookDto createBook(@RequestBody @Valid BookCreateDto bookCreateDto) {
        return bookService.createBook(bookCreateDto);
    }

    @PutMapping()
    BookDto updateBook(@RequestBody @Valid BookUpdateDto bookUpdateDto) {
        return bookService.updateBook(bookUpdateDto);
    }

    @DeleteMapping("/{id}")
    void deleteBook(@PathVariable("id") Long id) {
        bookService.deleteBook(id);
    }
}