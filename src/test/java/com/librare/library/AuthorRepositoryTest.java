package com.librare.library;

import com.librare.library.author.AuthorEntity;
import com.librare.library.author.AuthorRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

@DataJpaTest
class AuthorRepositoryTest {

    @Autowired
    private AuthorRepository authorRepository;

    @Test
    void testFindByNameContainingIgnoreCase() {
        AuthorEntity author1 = new AuthorEntity();
        author1.setId("1");
        author1.setName("Test Author");
        authorRepository.save(author1);

        AuthorEntity author2 = new AuthorEntity();
        author2.setId("2");
        author2.setName("Another Author");
        authorRepository.save(author2);

        List<AuthorEntity> result = authorRepository.findByNameContainingIgnoreCase("test");

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Test Author", result.get(0).getName());
    }

}
