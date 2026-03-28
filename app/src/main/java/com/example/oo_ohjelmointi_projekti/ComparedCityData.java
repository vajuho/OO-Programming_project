package com.example.oo_ohjelmointi_projekti;

import java.util.ArrayList;

public class ComparedCityData {
    private static ComparedCityData instance;
    private String name;
    private ArrayList<PopulationData> populationList;
    private WeatherData weatherData;

    private ComparedCityData() {}

    public static ComparedCityData getInstance() {
        if (instance == null) {
            instance = new ComparedCityData();
        }
        return instance;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<PopulationData> getPopulationList() {
        return populationList;
    }

    public void setPopulationList(ArrayList<PopulationData> populationList) {
        this.populationList = populationList;
    }

    public WeatherData getWeatherData() {
        return weatherData;
    }

    public void setWeatherData(WeatherData weatherData) {
        this.weatherData = weatherData;
    }
}
