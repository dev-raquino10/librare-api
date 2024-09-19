package com.librare.library.book;

import com.librare.library.author.AuthorEntity;
import com.librare.library.genre.GenreEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;


import java.util.List;

@Data
@Entity
@Table(name = "books")
public class BookEntity {

    @Id
    private String id;
    private String title;
    private List<AuthorEntity> authors;
    private List<GenreEntity> genres;
    private String publishDate;
}
