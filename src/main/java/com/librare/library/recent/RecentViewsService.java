package com.librare.library.recent;

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

    // Adiciona um livro visualizado recentemente
    public void addRecentBook(String bookId) {
        addToRecent(RECENT_BOOKS_KEY, bookId);
    }

    // Adiciona um autor visualizado recentemente
    public void addRecentAuthor(String authorId) {
        addToRecent(RECENT_AUTHORS_KEY, authorId);
    }

    // Adiciona um gênero visualizado recentemente
    public void addRecentGenre(String genreId) {
        addToRecent(RECENT_GENRES_KEY, genreId);
    }

    // Adiciona item à lista de recentes e limita o tamanho
    private void addToRecent(String key, String itemId) {
        redisTemplate.opsForList().leftPush(key, itemId);
        redisTemplate.opsForList().trim(key, 0, MAX_RECENT_ITEMS - 1);
    }

    // Retorna os últimos livros visualizados
    public List<String> getRecentBooks() {
        return getRecent(RECENT_BOOKS_KEY);
    }

    // Retorna os últimos autores visualizados
    public List<String> getRecentAuthors() {
        return getRecent(RECENT_AUTHORS_KEY);
    }

    // Retorna os últimos gêneros visualizados
    public List<String> getRecentGenres() {
        return getRecent(RECENT_GENRES_KEY);
    }

    // Recupera a lista de recentes do Redis
    private List<String> getRecent(String key) {
        List<String> recentItems = redisTemplate.opsForList().range(key, 0, -1);
        return recentItems != null ? recentItems : List.of();
    }
}