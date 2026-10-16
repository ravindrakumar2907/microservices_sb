package com.tech.gateway.cache;

import com.tech.gateway.bean.SessionInfo;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Simple in-memory cache repository for session data.
 * Uses ConcurrentHashMap for thread-safe operations.
 *
 * @param <T> the type of cached values
 */
@Component
public class LocalCacheRepository<T> {

    private final Map<String, T> cache = new ConcurrentHashMap<>();

    public void save(SessionInfo sessionInfo) {
        @SuppressWarnings("unchecked")
        T value = (T) sessionInfo;
        cache.put(sessionInfo.getToken(), value);
    }

    public Optional<T> get(String key) {
        return Optional.ofNullable(cache.get(key));
    }

    public void remove(String key) {
        cache.remove(key);
    }

    public int getCount() {
        return cache.size();
    }
}
