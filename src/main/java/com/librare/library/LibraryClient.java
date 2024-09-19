package com.librare.library;

import com.librare.library.author.AuthorDto;
import com.librare.library.book.BookDto;
import com.librare.library.genre.GenreDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "openLibraryClient", url = "https://openlibrary.org")
public interface LibraryClient {

    @GetMapping("/api/books/{bibkeys}")
    BookDto getBookById(@PathVariable("bibkeys") String bibkeys, @RequestParam("format") String format, @RequestParam("jscmd") String jscmd);

    @GetMapping("/search/authors.json")
    AuthorDto searchAuthorByName(@RequestParam("q") String authorName);

    @GetMapping("/subjects/{genre}.json")
    GenreDto searchBooksByGenre(@PathVariable("genre") String genre);
}