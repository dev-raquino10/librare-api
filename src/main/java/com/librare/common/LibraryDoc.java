package com.librare.common;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class LibraryDoc {
    private String key;
    private String title;
    private List<String> author_name;
    private String cover_i;
}