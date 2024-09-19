package com.librare.library;

import com.librare.library.recent.RecentViewsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class RecentViewsServiceTest {

    @Mock
    private CacheManager cacheManager;

    @Mock
    private Cache cache;

    @InjectMocks
    private RecentViewsService recentViewsService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddRecentBook() {
        when(cacheManager.getCache("recentBooks")).thenReturn(cache);
        List<String> items = new ArrayList<>();
        when(cache.get("items", List.class)).thenReturn(items);

        recentViewsService.addRecentBook("bookId1");

        verify(cache, times(1)).put(eq("items"), anyList());
    }

    @Test
    void testAddRecentAuthor() {
        when(cacheManager.getCache("recentAuthors")).thenReturn(cache);
        List<String> items = new ArrayList<>();
        when(cache.get("items", List.class)).thenReturn(items);

        recentViewsService.addRecentAuthor("author1");

        verify(cache, times(1)).put(eq("items"), anyList());
    }

    @Test
    void testAddRecentGenre() {
        when(cacheManager.getCache("recentGenres")).thenReturn(cache);
        List<String> items = new ArrayList<>();
        when(cache.get("items", List.class)).thenReturn(items);

        recentViewsService.addRecentGenre("genre1");

        verify(cache, times(1)).put(eq("items"), anyList());
    }

    @Test
    void testGetRecentBooks() {
        List<String> recentBooks = List.of("book1", "book2");
        when(cacheManager.getCache("recentBooks")).thenReturn(cache);
        when(cache.get("items", List.class)).thenReturn(recentBooks);

        List<String> result = recentViewsService.getRecentBooks();

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("book1", result.get(0));
    }

    @Test
    void testGetRecentAuthors() {
        List<String> recentAuthors = List.of("author1", "author2");
        when(cacheManager.getCache("recentAuthors")).thenReturn(cache);
        when(cache.get("items", List.class)).thenReturn(recentAuthors);

        List<String> result = recentViewsService.getRecentAuthors();

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("author1", result.get(0));
    }

    @Test
    void testGetRecentGenres() {
        List<String> recentGenres = List.of("genre1", "genre2");
        when(cacheManager.getCache("recentGenres")).thenReturn(cache);
        when(cache.get("items", List.class)).thenReturn(recentGenres);

        List<String> result = recentViewsService.getRecentGenres();

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("genre1", result.get(0));
    }

    @Test
    void testAddRecentBook_Exception() {
        doThrow(new RuntimeException("Cache Error")).when(cacheManager).getCache("recentBooks");

        assertDoesNotThrow(() -> recentViewsService.addRecentBook("book1"));
    }

    @Test
    void testAddRecentAuthor_Exception() {
        doThrow(new RuntimeException("Cache Error")).when(cacheManager).getCache("recentAuthors");

        assertDoesNotThrow(() -> recentViewsService.addRecentAuthor("author1"));
    }

    @Test
    void testAddRecentGenre_Exception() {
        doThrow(new RuntimeException("Cache Error")).when(cacheManager).getCache("recentGenres");

        assertDoesNotThrow(() -> recentViewsService.addRecentGenre("genre1"));
    }

    @Test
    void testGetRecentBooks_Exception() {
        doThrow(new RuntimeException("Cache Error")).when(cacheManager).getCache("recentBooks");

        List<String> result = recentViewsService.getRecentBooks();
        assertTrue(result.isEmpty());
    }

    @Test
    void testGetRecentAuthors_Exception() {
        doThrow(new RuntimeException("Cache Error")).when(cacheManager).getCache("recentAuthors");

        List<String> result = recentViewsService.getRecentAuthors();
        assertTrue(result.isEmpty());
    }

    @Test
    void testGetRecentGenres_Exception() {
        doThrow(new RuntimeException("Cache Error")).when(cacheManager).getCache("recentGenres");

        List<String> result = recentViewsService.getRecentGenres();
        assertTrue(result.isEmpty());
    }

    @Test
    void testAddToRecent_Exception() {
        Cache cache = mock(Cache.class);
        when(cacheManager.getCache("recentBooks")).thenReturn(cache);
        doThrow(new RuntimeException("Cache Update Error")).when(cache).put(anyString(), any());

        assertDoesNotThrow(() -> recentViewsService.addRecentBook("book1"));
    }
}
