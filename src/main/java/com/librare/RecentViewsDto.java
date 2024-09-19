package com.librare;

import lombok.Data;

import java.util.List;

@Data
public class RecentViewsDto {
    private List<String> books;
    private List<String> authors;
    private List<String> genres;
}