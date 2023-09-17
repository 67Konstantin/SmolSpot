package com.example.myapplication101.api.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Photo {
    @Expose
    @SerializedName("id")
    private String id;

    @Expose
    @SerializedName("route_id")
    private String routeId;

    @Expose
    @SerializedName("name")
    private String name;

    @Expose
    @SerializedName("data")
    private String data;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRouteId() {
        return routeId;
    }

    public void setRouteId(String routeId) {
        this.routeId = routeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
