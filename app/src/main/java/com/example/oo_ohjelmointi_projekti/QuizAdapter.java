package com.example.oo_ohjelmointi_projekti;


import static androidx.core.content.ContextCompat.startActivity;

import android.content.Context;
import android.content.Intent;
import android.hardware.camera2.CameraExtensionSession;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class QuizAdapter extends RecyclerView.Adapter<QuizViewHolder> {
    private Context context;
    private OnQuizClickListener listener;

    public interface OnQuizClickListener {
        void onCityClick(String cityName);
    }

    public QuizAdapter(Context context, OnQuizClickListener listener) {
        this.context = context;
        this.listener = listener;
    }

    @NonNull
    @Override
    public QuizViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new QuizViewHolder(LayoutInflater.from(context).inflate(R.layout.question_view, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull LatestCityViewHolder holder, int position) {
        ArrayList<String> cities = LatestCities.getInstance().getCities();
        if (position >= cities.size()) {
            return;
        }
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

