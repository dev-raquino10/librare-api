package com.librare.library.book;

import com.librare.library.author.AuthorEntity;
import com.librare.library.genre.GenreEntity;
import jakarta.persistence.*;
import lombok.Data;


import java.util.List;

@Entity
@Table(name = "books")
@Data
public class BookEntity {

    @Id
    private String id;
    private String title;
    private String publishDate;
    private String coverUrl;
    private String isbn;
    private Integer numberOfPages;

    @ManyToMany
    @JoinTable(name = "book_authors",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "author_id"))
    private List<AuthorEntity> authors;

    @ManyToMany
    @JoinTable(name = "book_genres",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id"))
    private List<GenreEntity> genres;
}
