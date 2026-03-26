package com.example.oo_ohjelmointi_projekti;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public class LatestCityAdapter {
    private ArrayList<String> latestCities= new ArrayList<>();
    private Context context;

    public LatestCityAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public LatestCityViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new LatestCityViewHolder(LayoutInflater.from(context).inflate(R.layout.city_view, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull LatestCityViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return .size();
    }
}
