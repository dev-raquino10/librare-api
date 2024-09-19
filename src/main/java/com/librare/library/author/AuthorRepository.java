package com.librare.library.author;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

    @Repository
    public interface AuthorRepository extends JpaRepository<AuthorEntity, String> {
        AuthorEntity findByName(String name);
    }