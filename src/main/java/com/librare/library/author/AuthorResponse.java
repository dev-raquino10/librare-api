package com.librare.library.author;

import lombok.Data;

import java.util.List;

@Data
public class AuthorResponse {
    private String key;
    private String name;
    private String birthDate;
    private String deathDate;
    private List<String> works;
}