package com.example.myapplication101;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.RatingBar;
import android.widget.TextView;

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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_route_detail);
        //findViewById
        {
            map = findViewById(R.id.map);
            tv_titleRoute = findViewById(R.id.textRouteTitleDetail);
            tv_descriptionRoute = findViewById(R.id.textRouteDescriptionDetail);
            rb_rating = findViewById(R.id.ratingRouteDetail);
            tv_routePath = findViewById(R.id.textRoutePath);
        }
        //getIntent
        {
            Intent intent = getIntent();
            title = getIntent().getStringExtra("title");
            description = getIntent().getStringExtra("description");
            id = getIntent().getStringExtra("id");
            rating = getIntent().getFloatExtra("rating", 0.0f);
        }


        ArrayList<LatLng> coordinates;
        LatLng point1 = new LatLng(54.7818, 32.0401); // Координаты первой точки
        LatLng point2 = new LatLng(54.7819, 32.0410); // Координаты второй точки
        LatLng point3 = new LatLng(54.7820, 32.0415); // Координаты третьей точки
        LatLng point4 = new LatLng(54.7822, 32.0420); // Координаты четвёртой точки

        coordinates = new ArrayList<>();
        coordinates.add(point1);
        coordinates.add(point2);
        coordinates.add(point3);
        coordinates.add(point4);

        LatLng cameraLocation = new LatLng(54.7819, 32.0412);
        routePath = "Крепостная стена, сад Блонье, улица Ленина и Маяковского";
        float zoom = 17f;
        route = new Route(title, description, rating, routePath, id, coordinates, zoom, cameraLocation);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);

        Objects.requireNonNull(mapFragment).getMapAsync(this);

        tv_titleRoute.setText(title);
        tv_descriptionRoute.setText(description);
        rb_rating.setRating(rating);
        tv_routePath.setText("Где проходит: " + route.getRoutePath());

        mapFragment.getMapAsync(this);

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
        this.gMap.moveCamera(CameraUpdateFactory.newLatLngZoom(route.getCameraLocation(), route.getZoom()));


    }
}