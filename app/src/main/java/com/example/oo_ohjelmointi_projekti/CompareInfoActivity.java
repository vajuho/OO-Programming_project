package com.example.oo_ohjelmointi_projekti;

import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class CompareInfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_compare_info);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        TextView cityOneNameText = findViewById(R.id.City1NameText);
        TextView cityTwoNameText = findViewById(R.id.City2NameText);
        TextView cityOnePopulationAmountText = findViewById(R.id.City1PopulationAmountText);
        TextView cityTwoPopulationAmountText = findViewById(R.id.City2PopulationAmountText);
        TextView cityOneTemperatureText = findViewById(R.id.City1TemperatureText);
        TextView cityTwoTemperatureText = findViewById(R.id.City2TemperatureText);

        MunicipalityData municipalityData = MunicipalityData.getInstance();
        PopulationDataStorage populationDataStorage = PopulationDataStorage.getInstance();

        cityOneNameText.setText(populationDataStorage.getMunicipality());
        cityOnePopulationAmountText.setText(String.valueOf(municipalityData.getPopulations().get(municipalityData.getPopulations().size() - 1).getAmount()));
        cityOneTemperatureText.setText(municipalityData.getWeather().getTemperature());


    }
}