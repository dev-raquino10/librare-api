package com.librare.library.author;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
    public interface AuthorRepository extends JpaRepository<AuthorEntity, String> {
        List<AuthorEntity> findByNameContainingIgnoreCase(String name);
    }