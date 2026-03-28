package com.example.oo_ohjelmointi_projekti.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.oo_ohjelmointi_projekti.ComparedCityData;
import com.example.oo_ohjelmointi_projekti.MunicipalityData;
import com.example.oo_ohjelmointi_projekti.PopulationData;
import com.example.oo_ohjelmointi_projekti.PopulationDataStorage;
import com.example.oo_ohjelmointi_projekti.R;

import java.util.ArrayList;

public class CompareFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_compare, container, false);

        MunicipalityData cityOne = MunicipalityData.getInstance();
        ComparedCityData cityTwo = ComparedCityData.getInstance();

        ArrayList<PopulationData> cityOnePopulationList = cityOne.getPopulations();
        ArrayList<PopulationData> cityTwoPopulationList = cityTwo.getPopulationList();

        if (cityOnePopulationList == null || cityTwoPopulationList == null) {
            return view;
        }

        int cityOnePopulation = cityOnePopulationList.get(cityOnePopulationList.size() - 1).getAmount();
        int cityTwoPopulation = cityTwoPopulationList.get(cityTwoPopulationList.size() - 1).getAmount();

        double cityOneTemp = cityOne.getWeather().getTemperature();
        double cityTwoTemp = cityTwo.getWeatherData().getTemperature();

        TextView City0neNameText = view.findViewById(R.id.CityOneNameText);
        TextView CityTwoNameText = view.findViewById(R.id.CityTwoNameText);
        TextView CityOnePopulationAmountText = view.findViewById(R.id.CityOnePopulationAmountText);
        TextView CityTwoPopulationAmountText = view.findViewById(R.id.CityTwoPopulationAmountText);
        TextView CityOneTemperatureText = view.findViewById(R.id.CityOneTemperatureText);
        TextView CityTwoTemperatureText = view.findViewById(R.id.CityTwoTemperatureText);

        City0neNameText.setText(PopulationDataStorage.getInstance().getMunicipality());
        CityTwoNameText.setText(cityTwo.getName());
        CityOnePopulationAmountText.setText("Väkiluku: " + cityOnePopulation);
        CityTwoPopulationAmountText.setText(String.valueOf(cityTwoPopulation));
        CityOneTemperatureText.setText(String.format("Lämpötila: %.1f °C", cityOneTemp));
        CityTwoTemperatureText.setText(String.format("%.1f °C", cityTwoTemp));

        return view;
    }
}