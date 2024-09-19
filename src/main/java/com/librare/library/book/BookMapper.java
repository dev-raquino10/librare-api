package com.librare.library.book;

import com.librare.library.author.AuthorDto;
import com.librare.library.author.AuthorEntity;
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

    public BookEntity toEntity(BookResponse bookResponse) {
        if (bookResponse == null) return null;

        BookEntity bookEntity = new BookEntity();
        bookEntity.setId(bookResponse.getKey());
        bookEntity.setTitle(bookResponse.getTitle());
        bookEntity.setPublishDate(bookResponse.getPublishDate());
        bookEntity.setCoverUrl(bookResponse.getCoverUrl());

        List<AuthorEntity> authors = bookResponse.getAuthors().stream()
                .map(authorDto -> authorRepository.findByName(authorDto.getName()))
                .collect(Collectors.toList());
        bookEntity.setAuthors(authors);

        List<GenreEntity> genres = bookResponse.getSubjects().stream()
                .map(genreName -> genreRepository.findByName(genreName))
                .collect(Collectors.toList());
        bookEntity.setGenres(genres);

        bookEntity.setIsbn(bookResponse.getIsbn());
        bookEntity.setNumberOfPages(bookResponse.getNumberOfPages());

        return bookEntity;
    }

    public BookResponse toResponse(BookEntity bookEntity) {
        if (bookEntity == null) return null;

        BookResponse bookResponse = new BookResponse();
        bookResponse.setKey(bookEntity.getId());
        bookResponse.setTitle(bookEntity.getTitle());
        bookResponse.setPublishDate(bookEntity.getPublishDate());
        bookResponse.setCoverUrl(bookEntity.getCoverUrl());

        bookResponse.setAuthors(bookEntity.getAuthors().stream()
                .map(author -> new AuthorDto(author.getId(), author.getName()))
                .collect(Collectors.toList()));

        bookResponse.setSubjects(bookEntity.getGenres().stream()
                .map(GenreEntity::getName)
                .collect(Collectors.toList()));

        bookResponse.setIsbn(bookEntity.getIsbn());
        bookResponse.setNumberOfPages(bookEntity.getNumberOfPages());

        return bookResponse;
    }
}