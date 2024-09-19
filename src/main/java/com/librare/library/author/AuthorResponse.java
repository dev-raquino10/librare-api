package com.librare.library.author;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class AuthorResponse {
    private AuthorDetails author;

    @Data
    @Builder
    @AllArgsConstructor
    public static class AuthorDetails {
        private String name;
        private List<BookDetails> book;
    }

    @Data
    @Builder
    @AllArgsConstructor
    public static class BookDetails {
        private String title;
        private String publishDate;
        private String isbn;
        private int numberOfPages;
    }
}