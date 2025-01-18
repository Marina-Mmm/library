package ru.itgirls.library.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.itgirls.library.dto.BookDto;
import ru.itgirls.library.service.BookService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @GetMapping("/books")
    List<BookDto> getBooksByName(@RequestParam("name") String name) {
        return bookService.getByNameV1(name);
    }
}
