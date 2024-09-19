package com.librare.library.author;

import org.springframework.stereotype.Component;

@Component
public class AuthorMapper {

    public AuthorEntity toEntity(AuthorResponse authorResponse) {
        if (authorResponse == null) return null;

        AuthorEntity authorEntity = new AuthorEntity();
        authorEntity.setId(authorResponse.getKey());
        authorEntity.setName(authorResponse.getName());
        authorEntity.setBirthDate(authorResponse.getBirthDate());
        authorEntity.setDeathDate(authorResponse.getDeathDate());

        return authorEntity;
    }

    public AuthorResponse toResponse(AuthorEntity authorEntity) {
        if (authorEntity == null) return null;

        AuthorResponse authorResponse = new AuthorResponse();
        authorResponse.setKey(authorEntity.getId());
        authorResponse.setName(authorEntity.getName());
        authorResponse.setBirthDate(authorEntity.getBirthDate());
        authorResponse.setDeathDate(authorEntity.getDeathDate());

        return authorResponse;
    }
}