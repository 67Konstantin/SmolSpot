package com.example.myapplication101.api;

import androidx.annotation.NonNull;

import com.example.myapplication101.api.entity.Route;

import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Example {
    public static void main(String[] args) {
        NetworkService.getInstance()
                .getJSONApi()
                .getAllRoutes()
                .enqueue(new Callback<List<Route>>() {
                    @Override
                    public void onResponse(@NonNull Call<List<Route>> call, @NonNull Response<List<Route>> response) {
                        for (Route route : Objects.requireNonNull(response.body())) {
                            System.out.println(route.getId());
                        }

                    }

                    @Override
                    public void onFailure(@NonNull Call<List<Route>> call, @NonNull Throwable t) {
                        System.out.println("ERRR");
                    }
                });
    }
}
