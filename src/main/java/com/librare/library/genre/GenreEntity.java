package com.librare.library.genre;

import com.librare.library.book.BookEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "genres")
public class GenreEntity {

    @Id
    private String id;
    private String name;
    private List<BookEntity> books;
}