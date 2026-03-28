package com.example.oo_ohjelmointi_projekti;

import android.content.Context;
import android.hardware.camera2.CameraExtensionSession;
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
        ArrayList<String> cities = LatestCities.getInstance().getCities();
        if (position >= cities.size()) {
            return;
        }
        String cityName = cities.get(position);
        holder.municipalityButton.setText(cityName);
        holder.municipalityButton.setOnClickListener(view -> listener.onCityClick(cityName));
    }

    @Override
    public int getItemCount() {
        return LatestCities.getInstance().getCities().size();
    }

    public void addCity(String cityName) {
        LatestCities.getInstance().addCity(cityName);
        notifyDataSetChanged();
    }
}
