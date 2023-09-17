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
        //RatingBar ratingBar = convertView.findViewById(R.id.ratingRoute);

        String routeTitle = route.getTitle();
        String routeDescription = route.getDescription();
        float routeRating = route.getRating();


        titleTextView.setText(routeTitle);
        descriptionTextView.setText(routeDescription);
       // ratingBar.setRating(routeRating);

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, RouteDetailActivity.class);
                intent.putExtra("title", routeTitle);
                intent.putExtra("description", routeDescription);
                intent.putExtra("id", route.getId());
                intent.putExtra("rating", routeRating);

                context.startActivity(intent);
            }
        });



        return convertView;
    }
}
