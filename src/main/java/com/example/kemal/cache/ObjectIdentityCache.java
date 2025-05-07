package com.example.kemal.cache;

import java.util.WeakHashMap;

public class ObjectIdentityCache {
    private static final ObjectIdentityCache instance = new ObjectIdentityCache();
    private WeakHashMap<Object, Boolean> objectCache = new WeakHashMap<>();

    private ObjectIdentityCache() {
    }

    public static ObjectIdentityCache instance() {
        return instance;
    }

    public void put(Object key, Boolean value) {
        objectCache.put(key, value);
    }

    public Boolean get(Object key) {
        return objectCache.get(key);
    }

    public boolean isAnyTrue() {
        for (Boolean value : objectCache.values()) {
            if (value) {
                return true;
            }
        }
        return false;
    }
}
