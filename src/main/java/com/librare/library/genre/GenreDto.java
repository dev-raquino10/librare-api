package com.librare.library.genre;

import com.librare.library.book.BookDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class GenreDto {
    private String key;
    private String name;
    private List<BookDto> books;
}