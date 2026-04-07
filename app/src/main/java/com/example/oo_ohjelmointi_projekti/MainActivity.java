package com.example.oo_ohjelmointi_projekti;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {
    TextView StatusText;
    ImageView StatusImage;
    EditText CityNameEdit;
    LatestCityAdapter cityAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.SearchButton), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        CityNameEdit = findViewById(R.id.CityNameEdit);
        StatusText = findViewById(R.id.StatusText);
        StatusImage = findViewById(R.id.StatusImage);

        // Adapter with a click listener that fills the search field
        // and triggers a search when a city is clicked from the latest searches list (RecyclerView)
        cityAdapter = new LatestCityAdapter(getApplicationContext(), cityName -> {
            CityNameEdit.setText(cityName);
            searchData(cityName);
        });

        RecyclerView RecyclerView = findViewById(R.id.SearchedCitiesRV);
        RecyclerView.setLayoutManager(new LinearLayoutManager(this));
        RecyclerView.setAdapter(cityAdapter);
    }

    // This method is for the button in the MainActivity XML.
    public void searchDataMainActivity(View view) {
        String municipality = CityNameEdit.getText().toString().trim();
        if (!municipality.isEmpty()) {
            searchData(municipality);
        }
    }
    private void searchData(String cityName) {
        View view = new View(getApplicationContext());
        Context context = this;
        DataRetriever dr = new DataRetriever();
        ExecutorService service = Executors.newSingleThreadExecutor();
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                StatusText.setText("Haetaan......");
                Glide.with(MainActivity.this).asGif().load(R.drawable.loading).into(StatusImage);
            }
        });
        service.execute(new Runnable() {
            @Override
            public void run() {
                ArrayList<PopulationData> populationDataList = dr.getPopulation(context, cityName);
                String employmentRate = dr.getEmploymentRate(context, cityName);
                String employmentSelfSufficiency = dr.getEmploymentSelfSufficiency(context, cityName);
                String carAmount = dr.getCarAmount(context, cityName);
                ArrayList<String> wikiDataList = dr.getWikiData(cityName);
                WeatherData weatherData = dr.getWeather(cityName);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (wikiDataList == null || employmentRate == null) {
                            StatusText.setText("Haku epäonnistui, kaupunkia ei ole olemassa tai se on kirjoitettu väärin.");
                            Glide.with(MainActivity.this).asGif().load(R.drawable.fail).into(StatusImage);
                            return;
                        }
                        PopulationDataStorage populationStorage = PopulationDataStorage.getInstance();
                        MunicipalityData municipalityDataStorage = MunicipalityData.getInstance();

                        populationStorage.setMunicipality(cityName);
                        for (PopulationData i : populationDataList) {
                            populationStorage.addPopulationData(new PopulationData(i.getYear(), i.getAmount(), i.getPopulationIncrease()));
                        }
                        municipalityDataStorage.setPopulations(populationStorage.getPopulationList());

                        municipalityDataStorage.setEmploymentData(new EmploymentData(employmentRate, employmentSelfSufficiency));

                        municipalityDataStorage.setCarData(new CarData(carAmount));
                        if (wikiDataList.get(0).equals("Wikimedia-täsmennyssivu")) {
                            wikiDataList.set(0, "");
                        }
                        municipalityDataStorage.setWikiData(new WikipediaData(wikiDataList));

                        municipalityDataStorage.setWeather(weatherData);

                        cityAdapter.addCity(cityName);
                        StatusText.setText("Haku onnistui");
                        goToListInfo(view);
                    }
                });
            }
        });
    }
    public void goToListInfo(View view) {
        Intent intent = new Intent(this, ListInfoActivity.class);
        startActivity(intent);
    }
}