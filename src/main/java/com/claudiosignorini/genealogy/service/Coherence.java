package com.claudiosignorini.genealogy.service;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;

@Slf4j
public final class Coherence {

    private Coherence() {
        throw new AssertionError();
    }

    public static <T> void check(T obj0, T obj1, Class<T> type) {
        for (Field field: type.getDeclaredFields()) {
            try {
                field.setAccessible(true);
                Object val0 = field.get(obj0);
                Object val1 = field.get(obj1);
                if (val0 != null && val1 != null && !val0.equals(val1)) {
                    log.warn("Incoerency on {}.{}: first: {}; second: {}", type.getName(), field.getName(), val0, val1);
                }
            } catch (IllegalAccessException e) {
                log.warn(e.getMessage(), e);
            }
        }
    }

}
