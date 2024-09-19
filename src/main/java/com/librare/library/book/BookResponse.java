package com.librare.library.book;

import com.librare.library.author.AuthorDto;
import lombok.Data;

import java.util.List;

@Data
public class BookResponse {
    private String key;
    private String title;
    private List<AuthorDto> authors;
    private String publishDate;
    private String coverUrl;
    private List<String> subjects;
    private Integer numberOfPages;
    private String isbn;
}
