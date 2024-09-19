package com.librare.library;

import com.librare.library.recent.RecentViewsDto;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class RecentViewsDtoTest {

    @Test
    void testRecentViewsDto() {
        RecentViewsDto recentViewsDto = new RecentViewsDto();
        recentViewsDto.setBooks(List.of("Book1", "Book2"));
        recentViewsDto.setAuthors(List.of("Author1", "Author2"));
        recentViewsDto.setGenres(List.of("Genre1", "Genre2"));

        assertNotNull(recentViewsDto.getBooks());
        assertEquals(2, recentViewsDto.getBooks().size());
        assertEquals("Book1", recentViewsDto.getBooks().get(0));

        assertNotNull(recentViewsDto.getAuthors());
        assertEquals(2, recentViewsDto.getAuthors().size());
        assertEquals("Author1", recentViewsDto.getAuthors().get(0));

        assertNotNull(recentViewsDto.getGenres());
        assertEquals(2, recentViewsDto.getGenres().size());
        assertEquals("Genre1", recentViewsDto.getGenres().get(0));
    }
}
