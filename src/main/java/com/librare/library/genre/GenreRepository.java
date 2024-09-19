package com.librare.library.genre;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GenreRepository extends JpaRepository<GenreEntity, String> {
    List<GenreEntity> findByNameContainingIgnoreCase(String name);
}