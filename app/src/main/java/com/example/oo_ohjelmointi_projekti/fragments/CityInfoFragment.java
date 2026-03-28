package com.example.oo_ohjelmointi_projekti.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.oo_ohjelmointi_projekti.MunicipalityData;
import com.example.oo_ohjelmointi_projekti.PopulationDataStorage;
import com.example.oo_ohjelmointi_projekti.R;

public class CityInfoFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_city_info, container, false);

        TextView cityNameText = view.findViewById(R.id.CityOneNameText);
        TextView cityDescriptionText = view.findViewById(R.id.CityDescriptionText);
        TextView cityWikiUrlText = view.findViewById(R.id.CityWikiUrlText);
        TextView populationAmountText = view.findViewById(R.id.CityOnePopulationAmountText);
        TextView populationChangeText = view.findViewById(R.id.PopulationChangeText);
        TextView employmentRateText = view.findViewById(R.id.EmploymentRateText);
        TextView employmentSelfSufficiencyText = view.findViewById(R.id.EmploymentSelfSufficiencyText);
        TextView temperatureText = view.findViewById(R.id.TemperatureText);
        TextView weatherDescriptionText = view.findViewById(R.id.WeatherDescriptionText);
        TextView carAmountText = view.findViewById(R.id.CarAmountText);
        ImageView carImageView = view.findViewById(R.id.CarImageView);

        PopulationDataStorage populationDataStorage = PopulationDataStorage.getInstance();
        MunicipalityData municipalityData = MunicipalityData.getInstance();

        cityNameText.setText("Väkiluku: " + populationDataStorage.getMunicipality());
        cityDescriptionText.setText(municipalityData.getWikiData().getWikiUrlAndDescription().get(0));
        cityWikiUrlText.setText(municipalityData.getWikiData().getWikiUrlAndDescription().get(1));
        populationAmountText.setText(String.valueOf("Väkiluku: " + municipalityData.getPopulations().get(municipalityData.getPopulations().size() - 1).getAmount()));
        populationChangeText.setText(String.valueOf("Väestönmuutos: " + municipalityData.getPopulations().get(municipalityData.getPopulations().size() - 1).getPopulationIncrease()));
        employmentRateText.setText("Työllisyysaste: " + municipalityData.getEmploymentData().getEmploymentRate() + "%");
        employmentSelfSufficiencyText.setText("Työpaikkojen omavaraisuus: " + municipalityData.getEmploymentData().getEmploymentSelfSufficiency());
        temperatureText.setText("Lämpötila nyt: " + municipalityData.getWeather() + "°C");
        weatherDescriptionText.setText("Sää nyt: " + municipalityData.getWeather().getDescription());
        carAmountText.setText("Autojen määrä: " + municipalityData.getCarData().getCarAmount());

        int carAmount = Integer.parseInt(municipalityData.getCarData().getCarAmount());

        int tierOne = 2000;
        int tierTwo = 10000;
        int tierThree = 50000;

        if (carAmount < tierOne) {
            carImageView.setImageResource(R.drawable.car_tier_1);
        } else if (tierOne <= carAmount && carAmount < tierTwo) {
            carImageView.setImageResource(R.drawable.car_tier_2);
        } else if (tierTwo <= carAmount && carAmount < tierThree) {
            carImageView.setImageResource(R.drawable.car_tier_3);
        } else {
            carImageView.setImageResource(R.drawable.car_tier_4);
        }

        return view;
    }

    }
