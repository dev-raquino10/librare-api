package com.librare.library.book;

import com.librare.library.author.AuthorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<BookEntity, String> {

    List<BookEntity> findByAuthorsContaining(AuthorEntity author);

    List<BookEntity> findByGenresContaining(String genre);

    List<BookEntity> findAll();

}