package com.example.oo_ohjelmointi_projekti;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class ListInfoActivity extends AppCompatActivity {
    private final int tierOne = 2000;
    private final int tierTwo = 10000;
    private final int tierThree = 50000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_list_info);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        TextView cityNameText = findViewById(R.id.CityNameText);
        TextView cityDescriptionText = findViewById(R.id.CityDescriptionText);
        TextView cityWikiUrlText = findViewById(R.id.CityWikiUrlText);
        TextView populationAmountText = findViewById(R.id.PopulationAmountText);
        TextView populationChangeText = findViewById(R.id.PopulationChangeText);
        TextView employmentRateText = findViewById(R.id.EmploymentRateText);
        TextView employmentSelfSufficiencyText = findViewById(R.id.EmploymentSelfSufficiencyText);
        TextView temperatureText = findViewById(R.id.TemperatureText);
        TextView weatherDescriptionText = findViewById(R.id.WeatherDescriptionText);
        TextView carAmountText = findViewById(R.id.CarAmountText);
        ImageView carImageView = findViewById(R.id.CarImageView);

        MunicipalityData municipalityData = MunicipalityData.getInstance();
        PopulationDataStorage populationDataStorage = PopulationDataStorage.getInstance();

        cityNameText.setText(populationDataStorage.getMunicipality());
        cityDescriptionText.setText(municipalityData.getWikiData().getWikiUrlAndDescription().get(0));
        cityWikiUrlText.setText(municipalityData.getWikiData().getWikiUrlAndDescription().get(1));
        populationAmountText.setText(String.valueOf(municipalityData.getPopulations().get(municipalityData.getPopulations().size() - 1).getAmount()));
        populationChangeText.setText(String.valueOf(municipalityData.getPopulations().get(municipalityData.getPopulations().size() - 1).getPopulationIncrease()));
        employmentRateText.setText(municipalityData.getEmploymentData().getEmploymentRate());
        employmentSelfSufficiencyText.setText(municipalityData.getEmploymentData().getEmploymentSelfSufficiency());
        temperatureText.setText(municipalityData.getWeather().getTemperature());
        weatherDescriptionText.setText(municipalityData.getWeather().getDescription());
        carAmountText.setText(municipalityData.getCarData().getCarAmount());

        int carAmount = Integer.parseInt(municipalityData.getCarData().getCarAmount());

        if (carAmount < tierOne) {
            carImageView.setImageResource(R.drawable.car_tier_1);
        } else if (tierOne <= carAmount && carAmount < tierTwo) {
            carImageView.setImageResource(R.drawable.car_tier_2);
        } else if (tierTwo <= carAmount && carAmount < tierThree) {
            carImageView.setImageResource(R.drawable.car_tier_3);
        } else {
            carImageView.setImageResource(R.drawable.car_tier_4);
        }
    }
}