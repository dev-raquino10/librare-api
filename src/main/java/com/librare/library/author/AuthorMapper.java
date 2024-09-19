package com.librare.library.author;

import org.springframework.stereotype.Component;

@Component
public class AuthorMapper {

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