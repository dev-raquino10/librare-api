package com.librare.library;

import com.librare.library.author.AuthorDto;
import com.librare.library.book.BookDto;
import com.librare.library.genre.GenreDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class LibraryController {

    @Autowired
    private LibraryService libraryService;

    @GetMapping("/book/{id}")
    public BookDto getBookById(@PathVariable String id) {
        // Consulta no banco de dados primeiro
        // Se não encontrar, consultar na Open Library
        return libraryService.getBookById(id);
    }

    @GetMapping("/author")
    public AuthorDto getAuthorByName(@RequestParam String name) {
        // Consulta no banco de dados primeiro
        // Se não encontrar, consultar na Open Library
        return libraryService.searchAuthorByName(name);
    }

    @GetMapping("/genre")
    public GenreDto getBooksByGenre(@RequestParam String genre) {
        // Consulta no banco de dados primeiro
        // Se não encontrar, consultar na Open Library
        return libraryService.searchBooksByGenre(genre);
    }
}