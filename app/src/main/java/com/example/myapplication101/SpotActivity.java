package com.example.myapplication101;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.widget.Toast;

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
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.maps.DirectionsApi;
import com.google.maps.DirectionsApiRequest;
import com.google.maps.GeoApiContext;
import com.google.maps.internal.ExceptionsAllowedToRetry;
import com.google.maps.model.DirectionsResult;
import com.google.maps.model.DirectionsRoute;
import com.google.maps.model.TravelMode;
import com.google.maps.model.Unit;

import java.util.concurrent.TimeUnit;

public class SpotActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap gMap;
    private double startLat;
    private double startLng;
    private FusedLocationProviderClient fusedLocationProviderClient;
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spot);

        // Получение координат первой точки маршрута из Intent
        Intent intent = getIntent();
        startLat = intent.getDoubleExtra("startLat", 0.0);
        startLng = intent.getDoubleExtra("startLng", 0.0);

        // Инициализация карты
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.mapFragment);
        mapFragment.getMapAsync(this);

        // Инициализация FusedLocationProviderClient
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);

        // Проверка разрешения на доступ к местоположению
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            // Если разрешение предоставлено, выполните шаги по построению маршрута

        } else {
            // Если разрешение не предоставлено, запросите его
            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, LOCATION_PERMISSION_REQUEST_CODE);
        }
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        gMap = googleMap;

        // Добавление маркера на карту с координатами первой точки маршрута
        LatLng startPoint = new LatLng(startLat, startLng);
        gMap.addMarker(new MarkerOptions().position(startPoint));
      //  buildRoute();
        // Установка камеры на координаты первой точки
        gMap.moveCamera(CameraUpdateFactory.newLatLngZoom(startPoint, 15f));


    }

    // Метод для построения маршрута
    // Метод для построения маршрута
    // Метод для построения маршрута
    private void buildRoute() {
        // Создание объекта GeoApiContext с вашим API-ключом
        GeoApiContext geoApiContext = new GeoApiContext.Builder()
                .apiKey("Ваш_API_Ключ")
                .build();

        // Создание запроса на построение маршрута
        DirectionsApiRequest request = DirectionsApi.newRequest(geoApiContext)
                .origin(new com.google.maps.model.LatLng(startLat, startLng))
                .destination(new com.google.maps.model.LatLng(54.7825, 32.0430))
                .mode(TravelMode.WALKING) // Режим перемещения (автомобиль, пешеход и т. д.)
                .units(Unit.METRIC); // Единицы измерения расстояния и времени

        try {
            // Выполнение запроса и получение результатов
            DirectionsResult result = request
                    .await();

            // Обработка полученных результатов и отображение маршрута на карте
            // Вам нужно добавить маркеры и линии на карту на основе данных из result.

            if (result != null) {
                // Пример добавления маршрута на карту (этот код нужно настроить)
                for (DirectionsRoute route : result.routes) {
                    PolylineOptions polylineOptions = new PolylineOptions();
                    for (com.google.maps.model.LatLng latLng : route.overviewPolyline.decodePath()) {
                        LatLng point = new LatLng(latLng.lat, latLng.lng);
                        polylineOptions.add(point);
                    }
                    gMap.addPolyline(polylineOptions);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "Ошибка при построении маршрута.", Toast.LENGTH_SHORT).show();
        }
    }



    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == LOCATION_PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Если разрешение на доступ к местоположению предоставлено, выполните шаги по построению маршрута
                buildRoute();
            } else {
                // Если разрешение не предоставлено, сообщите пользователю
                Toast.makeText(this, "Доступ к местоположению не предоставлен.", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
