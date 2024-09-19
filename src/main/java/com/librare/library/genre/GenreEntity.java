package com.librare.library.genre;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.librare.library.book.BookEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "genres")
@Data
public class GenreEntity {

    @Id
    private String id;
    private String name;

    @ManyToMany(mappedBy = "genres")
    @JsonIgnore
    private List<BookEntity> books;
}