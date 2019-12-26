package com.example.sopcode.testretrofit;

import android.util.Log;

import com.example.sopcode.utils.JsonParse;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Converter;

final class GsonResponseBodyConverter<T> implements Converter<ResponseBody, T> {
    private final Gson gson;
    private final TypeAdapter<T> adapter;

    GsonResponseBodyConverter(Gson gson, TypeAdapter<T> adapter) {
        this.gson = gson;
        this.adapter = adapter;
    }

    @Override
    public T convert(ResponseBody value) throws IOException {
        JsonReader jsonReader = gson.newJsonReader(value.charStream());
        Log.d("convert ============== ", "" + JsonParse.toJson(adapter.read(jsonReader)));
        try {
            return adapter.read(jsonReader);
        } finally {
            value.close();
        }
    }
}
