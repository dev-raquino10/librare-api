package com.librare.common;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class LibraryResponse {
    private List<LibraryDoc> docs;
}