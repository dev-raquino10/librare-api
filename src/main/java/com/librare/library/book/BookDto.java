package com.librare.library.book;

import com.librare.library.author.AuthorDto;
import com.librare.library.genre.GenreDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class BookDto {
    private String key;
    private String title;
    private List<AuthorDto> authors;
    private List<GenreDto> subjects;
    private String publishDate;
    private String cover;
}