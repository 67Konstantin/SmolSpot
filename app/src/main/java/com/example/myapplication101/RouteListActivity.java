package com.example.myapplication101;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.List;

public class RouteListActivity extends AppCompatActivity {
    private ListView routslistView;
    private RouteAdapter routeAdapter;
    String routePath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_route_list);

        routslistView = findViewById(R.id.routslistView);


        routePath = "Крепостная стена, сад Блонье, улица Ленина и Маяковского";
        float zoom = 17f;
        //Изменить и брать с бека
        List<Route> routeList = new ArrayList<>();
        // Создаем список маршрутов

        Route krepostnayaStena = new Route("Крепостная стена", "Маршрут вдоль Смоленской крепостной стены", 4.2f, "123321");

        routeList.add(krepostnayaStena);


        // Добавьте здесь больше маршрутов по вашему усмотрению

        routeAdapter = new RouteAdapter(this, routeList);
        routslistView.setAdapter(routeAdapter);
    }
}