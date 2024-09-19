package com.librare.library.book;

import lombok.Data;

import java.util.List;

@Data
public class BookResponse {
    private String key;
    private String title;
    private List<String> authors;
    private List<String> genres;
    private String publishDate;
}

