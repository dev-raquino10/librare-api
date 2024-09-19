package com.librare.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RecentlyViewedService {

    @Autowired
    private RedisTemplate<String, List<Book>> redisTemplate;

    private static final String RECENTLY_VIEWED_KEY = "recently_viewed_books";

    public List<Book> getRecentlyViewedBooks() {
        return redisTemplate.opsForValue().get(RECENTLY_VIEWED_KEY);
    }

    public void addRecentlyViewedBook(Book book) {
        List<Book> recentBooks = redisTemplate.opsForValue().get(RECENTLY_VIEWED_KEY);
        if (recentBooks == null) {
            recentBooks = new ArrayList<>();
        }
        if (!recentBooks.contains(book)) {
            recentBooks.add(book);
            redisTemplate.opsForValue().set(RECENTLY_VIEWED_KEY, recentBooks);
        }
    }
}