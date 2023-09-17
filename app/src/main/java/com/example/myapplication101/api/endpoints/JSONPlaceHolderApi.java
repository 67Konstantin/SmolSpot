package com.example.myapplication101.api.endpoints;

import com.example.myapplication101.api.entity.Photo;
import com.example.myapplication101.api.entity.Route;
import com.example.myapplication101.api.entity.Spot;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface JSONPlaceHolderApi {
    @POST("/user/routes/get")
    Call<Route> getRoute(@Query("id") String id);

    @POST("/user/routes/get/all")
    Call<List<Route>> getAllRoutes();

    @POST("/user/routes/create")
    Call<Route> createRoute(@Query("title") String title, @Query("description") String description, @Query("duration") int duration);

    @POST("/user/spots/get")
    Call<Spot> getSpot(@Query("id") String id);

    @POST("/user/spots/get/all")
    Call<List<Spot>> getAllSpots(@Query("route_id") String route_id);

    @POST("/user/spots/create")
    Call<Spot> createSpot(@Query("route_id") String routeId, @Query("title") String title, @Query("description") String description, @Query("longtude") int lon, @Query("latitude") int lat);

    @POST("/photos/get")
    Call<Photo> getPhoto(@Query("route_id") String routeId);
}
