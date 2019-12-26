package com.example.sopcode.testretrofit;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface ApiService {
    @POST("/api/wap/wapIndex/getAppAllDomain")
    Call<Bean> getAddress(@Header("Content-Type") String type, @Header("memberToken") String token, @Header("stationCode") String app, @Header("systemtype") String systemtype, @Body RequestBody entity);
}
