package com.librare.library.book;

import com.librare.library.author.AuthorDto;
import com.librare.library.genre.GenreEntity;
import lombok.Data;

import java.util.List;

@Data
public class BookDto {
    private String key;
    private String title;
    private List<AuthorDto> authors;
    private List<GenreEntity> subjects;
    private String publishDate;
    private String cover;
}