package com.librare.library.author;

import com.librare.library.book.BookDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class AuthorDto {
    private String id;
    private String name;
    private List<BookDto> books;
}
