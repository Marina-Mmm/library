package ru.itgirls.library.controller.rest;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.itgirls.library.dto.BookCreateDto;
import ru.itgirls.library.dto.BookDto;
import ru.itgirls.library.dto.BookUpdateDto;
import ru.itgirls.library.service.BookService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/books")
public class BookRestController {

    private final BookService bookService;

    @GetMapping("/name")
    List<BookDto> getBooksByName(@RequestParam("name") String name) {
        return bookService.getBooksByName(name);
    }

    @PostMapping()
    BookDto createBook(@RequestBody BookCreateDto bookCreateDto) {
        return bookService.createBook(bookCreateDto);
    }

    @PutMapping()
    BookDto updateBook(@RequestBody BookUpdateDto bookUpdateDto) {
        return bookService.updateBook(bookUpdateDto);
    }

    @DeleteMapping("/{id}")
    void deleteBook(@PathVariable("id") Long id) {
        bookService.deleteBook(id);
    }
}
