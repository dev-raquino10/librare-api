package com.librare.library.book;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class BookResponse {
    private String title;
    private String author;
    private String genre;
    private String publishDate;
    private String isbn;
    private int numberOfPages;
}