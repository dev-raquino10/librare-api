package com.librare.library.book;

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
    private List<String> authors;
    private List<String> genres;
    private String publishDate;
}
