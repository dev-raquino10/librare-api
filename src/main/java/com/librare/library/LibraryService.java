package com.librare.library;

import com.librare.library.author.AuthorDto;
import com.librare.library.book.BookDto;
import com.librare.library.genre.GenreDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LibraryService {

    @Autowired
    private LibraryClient libraryClient;


    public BookDto getBookById(String id) {
        return libraryClient.getBookById(id, "json", "data");
    }

    public AuthorDto searchAuthorByName(String authorName) {
        return libraryClient.searchAuthorByName(authorName);
    }

    public GenreDto searchBooksByGenre(String genre) {
        return libraryClient.searchBooksByGenre(genre);
    }
}