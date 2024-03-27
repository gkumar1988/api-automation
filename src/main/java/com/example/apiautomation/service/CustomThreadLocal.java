package com.example.apiautomation.service;

import java.util.HashMap;
import java.util.Map;

public class CustomThreadLocal {

    private static final ThreadLocal<Map<String, String>> variables = new ThreadLocal<>();

    public static void setVariables(String key, String value) {
        Map<String, String> map = variables.get();
        if (map == null) {
            map = new HashMap<>();
            variables.set(map);
        }
        map.put(key, value);
    }

    public static String getValue(String key) {
        Map<String, String> map = variables.get();
        return map != null ? map.get(key) : null;
    }
}
