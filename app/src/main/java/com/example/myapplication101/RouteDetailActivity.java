package com.example.myapplication101;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.Objects;

public class RouteDetailActivity extends AppCompatActivity implements OnMapReadyCallback {

    GoogleMap gMap;
    FrameLayout map;
    private Route route;
    String id;
    String title, description, routePath;
    float rating;
    TextView tv_titleRoute, tv_descriptionRoute, tv_routePath;
    RatingBar rb_rating;
    private FusedLocationProviderClient fusedLocationProviderClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_route_detail);

        // findViewById
        {
            map = findViewById(R.id.map);
            tv_titleRoute = findViewById(R.id.textRouteTitleDetail);
            tv_descriptionRoute = findViewById(R.id.textRouteDescriptionDetail);
            rb_rating = findViewById(R.id.ratingRouteDetail);
            tv_routePath = findViewById(R.id.textRoutePath);
        }

        // Инициализация FusedLocationProviderClient
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);

        // Получение данных из Intent
        {
            Intent intent = getIntent();
            title = intent.getStringExtra("title");
            description = intent.getStringExtra("description");
            id = intent.getStringExtra("id");
            rating = intent.getFloatExtra("rating", 0.0f);
        }
        // Инициализация координат и других данных
        ArrayList<LatLng> coordinates = new ArrayList<>();
        LatLng point1 = new LatLng(54.7818, 32.0401);
        LatLng point2 = new LatLng(54.7819, 32.0410);
        LatLng point3 = new LatLng(54.7820, 32.0415);
        LatLng point4 = new LatLng(54.7822, 32.0420);
        coordinates.add(point1);
        coordinates.add(point2);
        coordinates.add(point3);
        coordinates.add(point4);

        LatLng cameraLocation = new LatLng(54.7819, 32.0412);
        routePath = "Крепостная стена, сад Блонье, улица Ленина и Маяковского";
        float zoom = 17f;

        // Создание объекта Route
        route = new Route(title, description, rating, routePath, id, coordinates, zoom, cameraLocation);

        // Инициализация карты и отображение данных
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        Objects.requireNonNull(mapFragment).getMapAsync(this);

        tv_titleRoute.setText(title);
        tv_descriptionRoute.setText(description);
        rb_rating.setRating(rating);
        tv_routePath.setText("Где проходит: " + route.getRoutePath());
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        this.gMap = googleMap;
        for (int i = 0; i < route.getCoordinates().size(); i++) {
            LatLng coordinate = route.getCoordinates().get(i);
            String title = String.valueOf(i + 1);

            MarkerOptions markerOptions = new MarkerOptions()
                    .position(coordinate)
                    .title(title);

            gMap.addMarker(markerOptions);
        }

        // Перемещение камеры на текущее местоположение
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(route.getCameraLocation(), route.getZoom()));

        // Проверка наличия разрешения на местоположение
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            // Включение отображения текущего местоположения на карте
            gMap.setMyLocationEnabled(true);

        } else {
            // Если разрешение не предоставлено, запросите его здесь
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
        }
    }
}
