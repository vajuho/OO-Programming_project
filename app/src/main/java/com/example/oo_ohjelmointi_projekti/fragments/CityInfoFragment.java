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

        TextView cityNameText = view.findViewById(R.id.CityNameText);
        TextView cityDescriptionText = view.findViewById(R.id.CityDescriptionText);
        TextView cityWikiUrlText = view.findViewById(R.id.cityWikiUrlText);
        TextView populationAmountText = view.findViewById(R.id.PopulationAmountText);
        TextView populationChangeText = view.findViewById(R.id.PopulationChangeText);
        TextView employmentRateText = view.findViewById(R.id.EmploymentRateText);
        TextView employmentSelfSufficiencyText = view.findViewById(R.id.EmploymentSelfSufficiencyText);
        TextView temperatureText = view.findViewById(R.id.TemperatureText);
        TextView weatherDescriptionText = view.findViewById(R.id.WeatherDescriptionText);
        TextView carAmountText = view.findViewById(R.id.CarAmountText);
        ImageView carImageView = view.findViewById(R.id.CarImageView);

        PopulationDataStorage populationDataStorage = PopulationDataStorage.getInstance();
        MunicipalityData municipalityData = MunicipalityData.getInstance();

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
