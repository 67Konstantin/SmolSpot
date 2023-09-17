package com.example.myapplication101;

import com.google.android.gms.maps.model.LatLng;

import java.io.Serializable;
import java.util.ArrayList;

public class Route implements Serializable {
    private String title;
    private String description;
    private String id;
    private float rating;
    private ArrayList<LatLng> coordinates; // Массив с координатами точек
    private float zoom; // Значение зума карты
    private LatLng cameraLocation; // Координаты камеры
    private String routePath; // Строка для хранения информации о маршруте, где проходит маршрут
    public Route(String title, String description, float rating, String routePath, String id, ArrayList<LatLng> coordinates, float zoom, LatLng cameraLocation) {
        this.title = title;
        this.description = description;
        this.rating = rating;
        this.id = id;
        this.coordinates = coordinates; // Инициализируем массив координат
        this.zoom = zoom;
        this.cameraLocation = cameraLocation;
        this.routePath = routePath;
    }

    public Route(String title, String description, float rating, String id) {
        this.title = title;
        this.description = description;
        this.rating = rating;
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public void setCoordinates(ArrayList<LatLng> coordinates) {
        this.coordinates = coordinates;
    }

    public void setZoom(float zoom) {
        this.zoom = zoom;
    }

    public void setCameraLocation(LatLng cameraLocation) {
        this.cameraLocation = cameraLocation;
    }

    public void setRoutePath(String routePath) {
        this.routePath = routePath;
    }



    public float getZoom() {
        return zoom;
    }

    public LatLng getCameraLocation() {
        return cameraLocation;
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
    public String getRoutePath() {
        return routePath;
    }



    public ArrayList<LatLng> getCoordinates() {
        return coordinates;
    }

    public void addCoordinate(LatLng coordinate) {
        coordinates.add(coordinate);
    }
}
