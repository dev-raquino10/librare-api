package com.librare.library.book;

import com.librare.library.author.AuthorDto;
import lombok.Data;

import java.util.List;

@Data
public class BookDto {
    private String key;
    private String title;
    private List<AuthorDto> authors;
    private List<String> subjects;
    private String publishDate;
    private String cover;
}