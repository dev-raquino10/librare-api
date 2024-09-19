package com.librare.library.book;

import com.librare.library.author.AuthorDto;
import com.librare.library.author.AuthorRepository;
import com.librare.library.genre.GenreEntity;
import com.librare.library.genre.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class BookMapper {

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private GenreRepository genreRepository;

    @Autowired
    private BookRepository bookRepository;

    public BookResponse toResponse(BookEntity bookEntity) {
        if (bookEntity == null) return null;

        BookResponse bookResponse = new BookResponse();
        bookResponse.setKey(bookEntity.getId());
        bookResponse.setTitle(bookEntity.getTitle());
        bookResponse.setPublishDate(bookEntity.getPublishDate());
        bookResponse.setCoverUrl(bookEntity.getCoverUrl());

        bookResponse.setAuthors(bookEntity.getAuthors().stream()
                .map(author -> {
                    List<BookDto> books = bookRepository.findByAuthorsContaining(author).stream()
                            .map(this::mapToBookDto)
                            .collect(Collectors.toList());

                    return new AuthorDto(author.getId(), author.getName(), books);
                })
                .collect(Collectors.toList()));

        bookResponse.setSubjects(bookEntity.getGenres().stream()
                .map(GenreEntity::getName)
                .collect(Collectors.toList()));

        bookResponse.setIsbn(bookEntity.getIsbn());
        bookResponse.setNumberOfPages(bookEntity.getNumberOfPages());

        return bookResponse;
    }

    private BookDto mapToBookDto(BookEntity book) {
        return new BookDto(book.getId(), book.getTitle(), null, null, book.getPublishDate(), book.getCoverUrl());
    }
}
