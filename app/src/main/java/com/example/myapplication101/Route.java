package com.example.myapplication101;

public class Route {
    private String title;
    private String description;
    private String id;
    private float rating;

    public Route(String title, String description, float rating) {
        this.title = title;
        this.description = description;
        this.rating = rating;
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public float getRating() {
        return rating;
    }
    public String getId() {
        return id;
    }
}
