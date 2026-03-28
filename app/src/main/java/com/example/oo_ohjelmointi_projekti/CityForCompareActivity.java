package com.example.oo_ohjelmointi_projekti;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CityForCompareActivity extends AppCompatActivity {
    EditText CityNameCompareEdit;
    Button CityCompareSearchButton;
    TextView StatusTextTwo;
    Button GoToCompare;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_city_for_compare);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        CityNameCompareEdit = findViewById(R.id.CityNameCompareEdit);
        CityCompareSearchButton = findViewById(R.id.CityCompareSearchButton);
        StatusTextTwo = findViewById(R.id.StatusTextTwo);
        GoToCompare = findViewById(R.id.GoToCompare);
        GoToCompare.setEnabled(false);

        CityCompareSearchButton.setOnClickListener(View -> {
            String cityName = CityNameCompareEdit.getText().toString().trim();
            if (!cityName.isEmpty()) {
                getCompareData(cityName);
            }
        });
    }

    public void getCompareData(String cityName) {
        Context context = this;
        DataRetriever dr = new DataRetriever();
        ExecutorService service = Executors.newSingleThreadExecutor();
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                StatusTextTwo.setText("Haetaan");
            }
        });
        service.execute(new Runnable() {
            @Override
            public void run() {
                ArrayList<PopulationData> populationList = dr.getPopulation(context, cityName);
                WeatherData weatherData = dr.getWeather(cityName);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (populationList == null) {
                            StatusTextTwo.setText("Haku epäonnistui, kaupunkia ei ole olemassa tai se on kirjoitettu väärin.");
                            return;
                        }
                        ComparedCityData.getInstance().setName(cityName);
                        ComparedCityData.getInstance().setWeatherData(weatherData);
                        ComparedCityData.getInstance().setPopulationList(populationList);
                        StatusTextTwo.setText("Haku onnistui");
                        GoToCompare.setEnabled(true);
                    }
                });
            }
        });
    }

    public void goToListInfoActivity(View view) {
        Intent intent = new Intent(this, ListInfoActivity.class);
        intent.putExtra("ShowCompareInListInfoActivity", true);  // We found putExtra from geeksforgeeks. More info in the project plan.
        startActivity(intent);
    }
}