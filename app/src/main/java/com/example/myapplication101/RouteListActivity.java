package com.example.myapplication101;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;
import static com.example.myapplication101.api.NetworkService.BASE_URL;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import com.example.myapplication101.api.NetworkService;
import com.example.myapplication101.api.endpoints.JSONPlaceHolderApi;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

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

        Route krepostnayaStena = new Route("Крепостная стена", "Маршрут вдоль Смоленской крепостной стены", "123321");


        routeList.add(krepostnayaStena);


        NetworkService.getInstance()
                .getJSONApi()
                .getAllRoutes()
                .enqueue(new Callback<List<com.example.myapplication101.api.entity.Route>>() {
                    @Override
                    public void onResponse(@NonNull Call<List<com.example.myapplication101.api.entity.Route>> call, @NonNull Response<List<com.example.myapplication101.api.entity.Route>> response) {
                        for (com.example.myapplication101.api.entity.Route route : Objects.requireNonNull(response.body())) {
                            Route route1 = new Route(route.getTitle(), route.getDescription(), route.getId());
                            Log.d(TAG, route1.getTitle()+"77777");
                            routeList.add(route1);
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<List<com.example.myapplication101.api.entity.Route>> call, @NonNull Throwable t) {
                        Toast.makeText(RouteListActivity.this, "Не получилось   ", Toast.LENGTH_SHORT).show();
                    }
                });

        // Добавьте здесь больше маршрутов по вашему усмотрению

        routeAdapter = new RouteAdapter(this, routeList);
        routslistView.setAdapter(routeAdapter);
    }

}