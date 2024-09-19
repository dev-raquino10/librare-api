package com.librare.library;

import com.librare.library.author.AuthorResponse;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Collections;

public class AuthorResponseTest {

    @Test
    void testAuthorResponseCreation() {
        AuthorResponse.BookDetails bookDetails = AuthorResponse.BookDetails.builder()
                .title("Book Title")
                .publishDate("2024-01-01")
                .isbn("1234567890")
                .numberOfPages(300)
                .build();

        AuthorResponse.AuthorDetails authorDetails = AuthorResponse.AuthorDetails.builder()
                .name("Author Name")
                .book(Collections.singletonList(bookDetails))
                .build();

        AuthorResponse authorResponse = AuthorResponse.builder()
                .author(authorDetails)
                .build();

        assertNotNull(authorResponse);
        assertEquals("Author Name", authorResponse.getAuthor().getName());
        assertEquals(1, authorResponse.getAuthor().getBook().size());
        assertEquals("Book Title", authorResponse.getAuthor().getBook().get(0).getTitle());
        assertEquals("2024-01-01", authorResponse.getAuthor().getBook().get(0).getPublishDate());
        assertEquals("1234567890", authorResponse.getAuthor().getBook().get(0).getIsbn());
        assertEquals(300, authorResponse.getAuthor().getBook().get(0).getNumberOfPages());
    }
}
