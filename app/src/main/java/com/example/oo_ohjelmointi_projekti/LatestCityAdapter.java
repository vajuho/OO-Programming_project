package com.example.oo_ohjelmointi_projekti;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class LatestCityAdapter extends RecyclerView.Adapter<LatestCityViewHolder> {
    private ArrayList<String> latestCities= new ArrayList<>();
    private Context context;
    private OnCityClickListener listener;

    public interface OnCityClickListener {
        void onCityClick(String cityName);
    }

    public LatestCityAdapter(Context context, OnCityClickListener listener) {
        this.context = context;
        this.listener = listener;
    }

    @NonNull
    @Override
    public LatestCityViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new LatestCityViewHolder(LayoutInflater.from(context).inflate(R.layout.city_view, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull LatestCityViewHolder holder, int position) {
        String cityName = latestCities.get(position);
        holder.municipalityButton.setText(cityName);
        holder.municipalityButton.setOnClickListener(view -> listener.onCityClick(cityName));
    }

    @Override
    public int getItemCount() {
        return latestCities.size();
    }

    public void addCity(String cityName) {
        latestCities.remove(cityName);
        latestCities.add(0, cityName);
        if (latestCities.size() > 4) {
            latestCities.remove(4);
        }
        notifyDataSetChanged();
    }
}
