package com.example.oo_ohjelmointi_projekti;

import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class LatestCityViewHolder extends RecyclerView.ViewHolder {
    public Button municipalityButton;
    public LatestCityViewHolder(@NonNull View itemView) {
        super(itemView);
        municipalityButton = itemView.findViewById(R.id.cityButton);
    }
}
