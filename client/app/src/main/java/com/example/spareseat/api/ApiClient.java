package com.example.spareseat.api;

import com.example.spareseat.BuildConfig;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    private static final String BASE_URL = BuildConfig.DEBUG
            ? "http://10.0.2.2:8080/"
            : "https://api.spareseat.com/";
    private static ApiService service;

    public static ApiService getService() {
        if (service == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            service = retrofit.create(ApiService.class);
        }
        return service;
    }
}
