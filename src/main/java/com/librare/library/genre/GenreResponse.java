package com.librare.library.genre;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class GenreResponse {
    private String genre;
    private List<BookDetails> book;

    @Data
    @Builder
    @AllArgsConstructor
    public static class BookDetails {
        private String title;
        private String author;
        private String publishDate;
        private String isbn;
        private int numberOfPages;
    }
}