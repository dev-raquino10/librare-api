package com.librare.common;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SearchResponse {
    private int numFound;
    private List<SearchDoc> docs;

    @Getter
    @Setter
    public static class SearchDoc {
        private String title_suggest;
        private List<String> author_name;
        private Integer cover_i;
        private Integer first_publish_year;
        private String key;
    }
}