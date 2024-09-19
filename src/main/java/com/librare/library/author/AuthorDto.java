package com.librare.library.author;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuthorDto {
    private String key;
    private String name;

    public AuthorDto(String key, String name) {
        this.key = key;
        this.name = name;
    }
}
