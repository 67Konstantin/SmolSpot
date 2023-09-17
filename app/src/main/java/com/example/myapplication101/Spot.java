package com.example.myapplication101;

public class Spot {
    private int id;
    private String name;
    private String description;
    private float longitude;
    private float latitude;

    // Конструктор класса Spot
    public Spot(int id, String name, String description, float longitude, float latitude) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    // Геттеры и сеттеры для полей класса
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }
    public Spot() {
        // Пустой конструктор
    }
}
