package com.example.oo_ohjelmointi_projekti;

import java.util.ArrayList;

public class MunicipalityData {
    private WeatherData weather;

    private CarData carData;

    private WikipediaData wikiUrl;

    private EmploymentData employmentData;

    private ArrayList<PopulationData> populations;

    private static MunicipalityData instance;
    private MunicipalityData() {
    }

    public static MunicipalityData getInstance() {
        if (instance == null) {
            instance = new MunicipalityData();
        }
        return instance;
    }

    public WeatherData getWeather() {
        return weather;
    }

    public void setWeather(WeatherData weather) {
        this.weather = weather;
    }

    public CarData getCarData() {
        return carData;
    }

    public void setCarData(CarData carData) {
        this.carData = carData;
    }

    public WikipediaData getWikiUrl() {
        return wikiUrl;
    }

    public void setWikiUrl(WikipediaData wikiUrl) {
        this.wikiUrl = wikiUrl;
    }

    public EmploymentData getEmploymentData() {
        return employmentData;
    }

    public void setEmploymentData(EmploymentData employmentData) {
        this.employmentData = employmentData;
    }

    public ArrayList<PopulationData> getPopulations() {
        return populations;
    }

    public void setPopulations(ArrayList<PopulationData> populations) {
        this.populations = populations;
    }
}
