package com.librare.library.recent;

import com.librare.common.utils.LogUtil;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RecentViewsService {

    private static final int MAX_RECENT_ITEMS = 10;
    private final CacheManager cacheManager;

    public RecentViewsService(CacheManager cacheManager) {
        this.cacheManager = cacheManager;
    }

    public void addRecentBook(String bookId) {
        try {
            LogUtil.logMessage("Adding recent book: " + bookId);
            addToRecent("recentBooks", bookId);
        } catch (Exception ex) {
            LogUtil.logStackTrace("Error adding recent book: " + bookId, true, ex);
        }
    }

    public void addRecentAuthor(String author) {
        try {
            LogUtil.logMessage("Adding recent author: " + author);
            addToRecent("recentAuthors", author);
        } catch (Exception ex) {
            LogUtil.logStackTrace("Error adding recent author: " + author, true, ex);
        }
    }

    public void addRecentGenre(String genreId) {
        try {
            LogUtil.logMessage("Adding recent genre: " + genreId);
            addToRecent("recentGenres", genreId);
        } catch (Exception ex) {
            LogUtil.logStackTrace("Error adding recent genre: " + genreId, true, ex);
        }
    }

    private void addToRecent(String cacheName, String itemId) {
        try {
            Cache cache = cacheManager.getCache(cacheName);
            if (cache != null) {
                LogUtil.logMessage("Cache found for: " + cacheName);
                List<String> recentItems = cache.get("items", List.class);
                if (recentItems == null) {
                    recentItems = new ArrayList<>();
                }
                recentItems.remove(itemId);
                recentItems.add(0, itemId);
                if (recentItems.size() > MAX_RECENT_ITEMS) {
                    recentItems = recentItems.subList(0, MAX_RECENT_ITEMS);
                }
                cache.put("items", recentItems);
                LogUtil.logObject("Updated recent items in cache", true, recentItems);
            } else {
                LogUtil.logMessage("Cache not found for: " + cacheName);
            }
        } catch (Exception ex) {
            LogUtil.logStackTrace("Error updating recent items in cache: " + cacheName, true, ex);
        }
    }

    public List<String> getRecentBooks() {
        try {
            LogUtil.logMessage("Fetching recent books");
            return getRecent("recentBooks");
        } catch (Exception ex) {
            LogUtil.logStackTrace("Error fetching recent books", true, ex);
            return List.of();
        }
    }

    public List<String> getRecentAuthors() {
        try {
            LogUtil.logMessage("Fetching recent authors");
            return getRecent("recentAuthors");
        } catch (Exception ex) {
            LogUtil.logStackTrace("Error fetching recent authors", true, ex);
            return List.of();
        }
    }

    public List<String> getRecentGenres() {
        try {
            LogUtil.logMessage("Fetching recent genres");
            return getRecent("recentGenres");
        } catch (Exception ex) {
            LogUtil.logStackTrace("Error fetching recent genres", true, ex);
            return List.of();
        }
    }

    private List<String> getRecent(String cacheName) {
        try {
            Cache cache = cacheManager.getCache(cacheName);
            if (cache != null) {
                List<String> recentItems = cache.get("items", List.class);
                LogUtil.logObject("Fetched recent items from cache: " + cacheName, true, recentItems);
                return recentItems != null ? recentItems : List.of();
            } else {
                LogUtil.logMessage("Cache not found for: " + cacheName);
                return List.of();
            }
        } catch (Exception ex) {
            LogUtil.logStackTrace("Error fetching recent items from cache: " + cacheName, true, ex);
            return List.of();
        }
    }
}
