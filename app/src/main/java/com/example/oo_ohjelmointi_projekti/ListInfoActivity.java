package com.example.oo_ohjelmointi_projekti;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ListInfoActivity extends AppCompatActivity {

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
        WikipediaData wikipediaData = new WikipediaData();

        cityNameText.setText(populationDataStorage.getMunicipality());
        cityDescriptionText.setText(wikipediaData.getWikiUrl().get(0));
        cityWikiUrlText.setText(wikipediaData.getWikiUrl().get(1));
        populationAmountText.setText()



    }
}