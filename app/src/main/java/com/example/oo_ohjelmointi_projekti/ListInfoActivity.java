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
        ImageView CarImageView = findViewById(R.id.CarImageView);

        /*CarDataStorage storage = CarDataStorage.getInstance();

        cityText.setText(storage.getCity());
        yearText.setText(String.valueOf(storage.getYear()));

        StringBuilder builder = new StringBuilder();
        int total = 0;

        for(CarData car : storage.getCarData()){
            builder.append(car.getType());
            builder.append(": ");
            builder.append(car.getAmount());
            builder.append("\n");

            total = total + car.getAmount();
        }

        builder.append("\nYhteensä: ").append(total);

        carInfoText.setText(builder.toString());*/
    }
}