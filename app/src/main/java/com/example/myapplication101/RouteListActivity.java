package com.example.myapplication101;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class RouteListActivity extends AppCompatActivity {
    private ListView routslistView;
    private RouteAdapter routeAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_route_list);

        routslistView = findViewById(R.id.routslistView);

        // Создаем список маршрутов
        List<Route> routeList = new ArrayList<>();
        routeList.add(new Route("Маршрут 1", "Описание маршрута 1", 4.5f));
        routeList.add(new Route("Маршрут 2", "Описание маршрута 2", 4.0f));
        // Добавьте здесь больше маршрутов по вашему усмотрению

        routeAdapter = new RouteAdapter(this, routeList);
        routslistView.setAdapter(routeAdapter);
    }
}