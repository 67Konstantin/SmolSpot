package com.example.myapplication101;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.myapplication101.Route;
import com.example.myapplication101.RouteDetailActivity;

import java.util.List;

public class RouteAdapter extends BaseAdapter {
    private List<Route> routeList;
    private Context context;

    public RouteAdapter(Context context, List<Route> routeList) {
        this.context = context;
        this.routeList = routeList;
    }

    @Override
    public int getCount() {
        return routeList.size();
    }

    @Override
    public Object getItem(int position) {
        return routeList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_route, parent, false);
        }

        Route route = routeList.get(position);

        TextView titleTextView = convertView.findViewById(R.id.textRouteTitle);
        TextView descriptionTextView = convertView.findViewById(R.id.textRouteDescription);
        RatingBar ratingBar = convertView.findViewById(R.id.ratingRoute);

        titleTextView.setText(route.getTitle());
        descriptionTextView.setText(route.getDescription());
        ratingBar.setRating(route.getRating());

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String routeId = route.getId(); // Предполагая, что у вашего объекта Route есть метод getId() для получения идентификатора
                Intent intent = new Intent(context, RouteDetailActivity.class);
                intent.putExtra("id", routeId);
                context.startActivity(intent);
            }
        });



        return convertView;
    }
}
