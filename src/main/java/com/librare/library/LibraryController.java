package com.librare.library;

import com.librare.RecentViewsDto;
import com.librare.library.author.AuthorDto;
import com.librare.library.book.BookDto;
import com.librare.library.genre.GenreDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class LibraryController {

    @Autowired
    private LibraryService libraryService;

    @Autowired
    private RecentViewsService recentViewsService;

    @GetMapping("/book/{id}")
    public BookDto getBookById(@PathVariable String id) {
        return libraryService.getBookById(id);
    }

    @GetMapping("/author")
    public AuthorDto getAuthorByName(@RequestParam String name) {
        return libraryService.searchAuthorByName(name);
    }

    @GetMapping("/genre")
    public GenreDto getBooksByGenre(@RequestParam String genre) {
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