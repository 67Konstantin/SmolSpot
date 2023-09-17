package com.example.myapplication101;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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

import java.util.ArrayList;
import java.util.List;

public class SpotActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap gMap;
    private double startLat;
    private double startLng;
    private FusedLocationProviderClient fusedLocationProviderClient;
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1;
    private List<LatLng> routePoints;
    private int currentPointIndex = 0;
    boolean fr = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spot);

        // Получение координат первой точки маршрута из Intent
        Intent intent = getIntent();
        startLat = intent.getDoubleExtra("startLat", 0.0);
        startLng = intent.getDoubleExtra("startLng", 0.0);

        // Получение списка координат маршрута из Intent
        routePoints = intent.getParcelableArrayListExtra("coordinates");

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

        // Настройка кнопки для перехода к следующей точке
        Button startNavigationButton = findViewById(R.id.startNavigationButton);
        startNavigationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!fr){
                    fr=true;
                    startNavigationButton.setText("Продолжить");
                }
                showNextPoint();
            }
        });
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        gMap = googleMap;

        // Добавление маркера на карту с координатами первой точки маршрута
        LatLng startPoint = new LatLng(startLat, startLng);
        gMap.addMarker(new MarkerOptions().position(startPoint));
        gMap.moveCamera(CameraUpdateFactory.newLatLngZoom(startPoint, 15f));
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            // Включение отображения текущего местоположения на карте
            gMap.setMyLocationEnabled(true);

        } else {
            // Если разрешение не предоставлено, запросите его здесь
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
        }
    }

    // Метод для отображения следующей точки маршрута
    // Метод для очистки карты и отображения следующей точки маршрута
    private void showNextPoint() {
        if (currentPointIndex < routePoints.size()) {
            // Очистка карты
            gMap.clear();

            LatLng nextLatLng = routePoints.get(currentPointIndex);

            // Установка маркера на следующей точке маршрута
            gMap.addMarker(new MarkerOptions().position(nextLatLng));

            // Перемещение камеры к следующей точке
            gMap.moveCamera(CameraUpdateFactory.newLatLngZoom(nextLatLng, 15f));

            // Увеличение индекса текущей точки
            currentPointIndex++;
        } else {
            // Маршрут завершен
            Toast.makeText(this, "Конец маршрута.", Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == LOCATION_PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Если разрешение на доступ к местоположению предоставлено, выполните шаги по построению маршрута

            } else {
                // Если разрешение не предоставлено, сообщите пользователю
                Toast.makeText(this, "Доступ к местоположению не предоставлен.", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
