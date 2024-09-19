package com.librare.library;

import com.librare.library.author.AuthorDto;
import com.librare.library.author.AuthorEntity;
import com.librare.library.author.AuthorRepository;
import com.librare.library.book.BookDto;
import com.librare.library.book.BookEntity;
import com.librare.library.book.BookRepository;
import com.librare.library.genre.GenreDto;
import com.librare.library.genre.GenreEntity;
import com.librare.library.genre.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LibraryService {

    @Autowired
    private LibraryClient libraryClient;

    @Autowired
    private RecentViewsService recentViewsService;


    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private AuthorRepository authorRepository;
    @Autowired
    private GenreRepository genreRepository;

    public BookDto getBookById(String id) {
        Optional<BookEntity> book = bookRepository.findById(id);
        if (book.isPresent()) {
            recentViewsService.addRecentBook(id);
            return mapToBookDto(book.get());
        }
        return libraryClient.getBookById(id, "json", "data");
    }

    public AuthorDto searchAuthorByName(String authorName) {
        Optional<AuthorEntity> author = Optional.ofNullable(authorRepository.findByName(authorName));
        if (author.isPresent()) {
            recentViewsService.addRecentAuthor(author.get().getId());
            return mapToAuthorDto(author.get());
        }

        AuthorDto authorDto = libraryClient.searchAuthorByName(authorName);
        recentViewsService.addRecentAuthor(authorDto.getKey());

        return authorDto;
    }

    public GenreDto searchBooksByGenre(String genre) {
        Optional<GenreEntity> genreEntity = Optional.ofNullable(genreRepository.findByName(genre));
        if (genreEntity.isPresent()) {
            recentViewsService.addRecentGenre(genreEntity.get().getId());
            return mapToGenreDto(genreEntity.get());
        }

        GenreDto genreDto = libraryClient.searchBooksByGenre(genre);
        recentViewsService.addRecentGenre(genreDto.getKey());

        return genreDto;
    }

    private BookDto mapToBookDto(BookEntity book) {
        BookDto bookDto = new BookDto();
        bookDto.setKey(book.getId());
        bookDto.setTitle(book.getTitle());
        bookDto.setAuthors(mapToAuthorDtos(book.getAuthors()));
        bookDto.setSubjects(book.getGenres());
        bookDto.setPublishDate(book.getPublishDate().toString());
        return bookDto;
    }

    private AuthorDto mapToAuthorDto(AuthorEntity author) {
        AuthorDto authorDto = new AuthorDto();
        authorDto.setKey(author.getId());
        authorDto.setName(author.getName());
        return authorDto;
    }

    private GenreDto mapToGenreDto(GenreEntity genre) {
        GenreDto genreDto = new GenreDto();
        genreDto.setKey(genre.getId());
        genreDto.setName(genre.getName());
        genreDto.setBooks(mapToBookDtos(genre.getBooks()));
        return genreDto;
    }

    private List<AuthorDto> mapToAuthorDtos(List<AuthorEntity> authors) {
        return authors.stream().map(this::mapToAuthorDto).collect(Collectors.toList());
    }

    private List<BookDto> mapToBookDtos(List<BookEntity> books) {
        return books.stream().map(this::mapToBookDto).collect(Collectors.toList());
    }
}