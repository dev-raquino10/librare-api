package com.librare.library;

import com.librare.library.author.AuthorDto;
import com.librare.library.book.BookDto;
import com.librare.library.genre.GenreDto;
import com.librare.library.recent.RecentViewsDto;
import com.librare.library.recent.RecentViewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/library")
public class LibraryController {

    @Autowired
    private LibraryService libraryService;

    @Autowired
    private RecentViewsService recentViewsService;

    @GetMapping("/books/{id}")
    public BookDto getBookById(@PathVariable String id) {
        return libraryService.getBookById(id);
    }

    @GetMapping("/books/all")
    public List<BookDto> getAllBooks() {
        return libraryService.getAllBooks();
    }

    @Cacheable(value = "authors", key = "#name")
    @GetMapping("/author/{name}")
    public AuthorDto getAuthorByName(@PathVariable String name) {
        return libraryService.searchAuthorByName(name);
    }

    @Cacheable(value = "genres", key = "#genre")
    @GetMapping("/genres/{name}")
    public GenreDto getBooksByGenre(@PathVariable String genre) {
        return libraryService.searchBooksByGenre(genre);
    }

    @GetMapping("/recent")
    public RecentViewsDto getRecentViews() {
        List<String> recentBooks = recentViewsService.getRecentBooks();
        List<String> recentAuthors = recentViewsService.getRecentAuthors();
        List<String> recentGenres = recentViewsService.getRecentGenres();

        RecentViewsDto recentViewsDto = new RecentViewsDto();
        recentViewsDto.setBooks(recentBooks);
        recentViewsDto.setAuthors(recentAuthors);
        recentViewsDto.setGenres(recentGenres);

        return recentViewsDto;
    }
}