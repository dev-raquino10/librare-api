package com.librare.library;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecentViewsService {

    private static final String RECENT_BOOKS_KEY = "recent:books";
    private static final String RECENT_AUTHORS_KEY = "recent:authors";
    private static final String RECENT_GENRES_KEY = "recent:genres";
    private static final int MAX_RECENT_ITEMS = 10;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    public void addRecentBook(String bookId) {
        addToRecent(RECENT_BOOKS_KEY, bookId);
    }

    public void addRecentAuthor(String authorId) {
        addToRecent(RECENT_AUTHORS_KEY, authorId);
    }

    public void addRecentGenre(String genreId) {
        addToRecent(RECENT_GENRES_KEY, genreId);
    }

    private void addToRecent(String key, String itemId) {
        redisTemplate.opsForList().leftPush(key, itemId);
        redisTemplate.opsForList().trim(key, 0, MAX_RECENT_ITEMS - 1);
    }

    public List<String> getRecentBooks() {
        return getRecent(RECENT_BOOKS_KEY);
    }

    public List<String> getRecentAuthors() {
        return getRecent(RECENT_AUTHORS_KEY);
    }

    public List<String> getRecentGenres() {
        return getRecent(RECENT_GENRES_KEY);
    }

    private List<String> getRecent(String key) {
        List<String> recentItems = redisTemplate.opsForList().range(key, 0, -1);
        return recentItems != null ? recentItems : List.of();
    }
}