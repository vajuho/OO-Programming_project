package com.example.oo_ohjelmointi_projekti;

import java.util.ArrayList;

public class LatestCities {
    private ArrayList<String> cities = new ArrayList<>();
    private static LatestCities instance;
    private LatestCities() {}
    public static LatestCities getInstance() {
        if (instance == null) {
            instance = new LatestCities();
        }
        return instance;
    }
    public ArrayList<String> getCities() {
        return cities;
    }
    public void addCity(String name) {
        cities.remove(name);
        cities.add(0, name);
        if (cities.size() > 4) {
            cities.remove(4);
        }
    }
}
