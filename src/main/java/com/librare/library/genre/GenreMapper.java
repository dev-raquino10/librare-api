package com.librare.library.genre;

import org.springframework.stereotype.Component;

@Component
public class GenreMapper {

    public GenreResponse toResponse(GenreEntity genreEntity) {
        if (genreEntity == null) return null;

        GenreResponse genreResponse = new GenreResponse();
        genreResponse.setName(genreEntity.getName());

        return genreResponse;
    }
}