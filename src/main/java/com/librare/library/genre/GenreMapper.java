package com.librare.library.genre;

import org.springframework.stereotype.Component;

@Component
public class GenreMapper {

    public GenreEntity toEntity(GenreResponse genreResponse) {
        if (genreResponse == null) return null;

        GenreEntity genreEntity = new GenreEntity();
        genreEntity.setId(genreResponse.getName());
        genreEntity.setName(genreResponse.getName());

        return genreEntity;
    }

    public GenreResponse toResponse(GenreEntity genreEntity) {
        if (genreEntity == null) return null;

        GenreResponse genreResponse = new GenreResponse();
        genreResponse.setName(genreEntity.getName());

        return genreResponse;
    }
}