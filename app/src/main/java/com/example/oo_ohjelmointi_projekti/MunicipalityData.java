package com.example.oo_ohjelmointi_projekti;

import java.util.ArrayList;

public class MunicipalityData {
    private WeatherData weather;

    private CarData carData;

    private WikipediaData wikiUrl;

    private EmploymentData employmentData;

    private ArrayList<PopulationData> populations;

    public MunicipalityData(WeatherData weather, CarData carData, WikipediaData wikiUrl, EmploymentData employmentData, ArrayList<PopulationData> populations) {
        this.weather = weather;
        this.carData = carData;
        this.wikiUrl = wikiUrl;
        this.employmentData = employmentData;
        this.populations = populations;
    }

    public WeatherData getWeather() {
        return weather;
    }

    public CarData getCarData() {
        return carData;
    }

    public WikipediaData getWikiUrl() {
        return wikiUrl;
    }

    public ArrayList<PopulationData> getPopulations() {
        return populations;
    }

    public EmploymentData getEmploymentData() {
        return employmentData;
    }
}
