package com.example.oo_ohjelmointi_projekti.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.oo_ohjelmointi_projekti.ComparedCityData;
import com.example.oo_ohjelmointi_projekti.MunicipalityData;
import com.example.oo_ohjelmointi_projekti.PopulationData;
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

        int cityOnePopulation = cityOnePopulationList.get(cityOnePopulationList.size() - 1).getAmount();
        int cityTwoPopulation = cityTwoPopulationList.get(cityTwoPopulationList.size() - 1).getAmount();

        double city1Temp = Double.parseDouble(cityOne.getWeather().getTemperature()) - 273.15;
        double city2Temp = Double.parseDouble(cityTwo.getWeatherData().getTemperature()) - 273.15;
    }
}