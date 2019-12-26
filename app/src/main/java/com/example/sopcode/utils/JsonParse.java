package com.example.sopcode.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.lang.reflect.Type;

public class JsonParse {
    private static final Gson parser;

    static {
        parser = new GsonBuilder().serializeNulls().create();
    }


    public static <T> T fromJson(String json, Class<T> cls) {
        try {
            return parser.fromJson(json, cls);
        } catch (Exception e) {

        }
        return null;
    }

    public static <T> T fromJson(String json, Type type) {
        try {
            return parser.fromJson(json, type);
        } catch (Exception e) {

        }
        return null;
    }

    public static String toJson(Object obj) {
        try {
            return parser.toJson(obj);
        } catch (Exception e) {
        }
        return null;
    }
}
