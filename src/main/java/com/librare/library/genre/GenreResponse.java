package com.librare.library.genre;

import com.librare.library.book.BookResponse;
import lombok.Data;

import java.util.List;

@Data
public class GenreResponse {
    private String name;
    private Integer workCount;
    private List<BookResponse> works;
}