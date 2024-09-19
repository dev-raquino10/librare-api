package com.librare.library.author;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.librare.library.book.BookEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "authors")
@Data
public class AuthorEntity {

    @Id
    private String id;
    private String name;
    private String birthDate;
    private String deathDate;
    @ManyToMany(mappedBy = "authors")
    @JsonIgnore
    private List<BookEntity> books;
}