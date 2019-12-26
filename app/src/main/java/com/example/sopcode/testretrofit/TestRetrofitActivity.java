package com.example.sopcode.testretrofit;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.sopcode.R;
import com.example.sopcode.utils.JsonParse;
import com.google.gson.GsonBuilder;

import org.json.JSONObject;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class TestRetrofitActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit);
        init();
    }

    private void init() {
        Button button = findViewById(R.id.buton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                request();
            }
        });
    }

    private void request() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        builder.addInterceptor(interceptor);

        Retrofit retrofit = new Retrofit.Builder()
                ///.addConverterFactory(GsonConverterFactory.create())
                .addConverterFactory(GsonDConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                //.addConverterFactory(GsonConverterFactory.create(new GsonBuilder().serializeNulls().create()))
                .baseUrl("https://puatapp205.bwt18.com")
                .client(builder.build())
                .build();
        ApiService homeApiService = retrofit.create(ApiService.class);
        JSONObject requestData = new JSONObject();
        try {
            requestData.put("version", "6.0.0.2");
        } catch (Exception e) {
            e.printStackTrace();
        }

        final RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), requestData.toString());
        Call<Bean> call = homeApiService.getAddress("application/json", "", "puat201", "android", requestBody);
        call.enqueue(new Callback<Bean>() {

            @Override
            public void onResponse(Call<Bean> call, Response<Bean> response) {
                Bean body = response.body();
                String json = JsonParse.toJson(body);
                Log.d("activity ============ ", "" + json);
            }

            @Override
            public void onFailure(Call<Bean> call, Throwable t) {
                Log.d("activity ============ ", "fail");
            }
        });
    }
}
