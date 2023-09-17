package com.example.myapplication101.api;

import com.example.myapplication101.api.endpoints.JSONPlaceHolderApi;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkService {
    public static final String BASE_URL = "https://backend.cube-hackaton.ru";
    private static NetworkService instance;

    private final Retrofit retrofit;

    public NetworkService() {
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public JSONPlaceHolderApi getJSONApi() {
        return retrofit.create(JSONPlaceHolderApi.class);
    }

    public static NetworkService getInstance() {
        if (instance == null)
            instance = new NetworkService();

        return instance;
    }
}
