package com.example.oo_ohjelmointi_projekti;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class PopulationDataStorage {
    private static PopulationDataStorage instance;
    private String municipality;
    private ArrayList<PopulationData> populationList = new ArrayList<>();

    private PopulationDataStorage() {
    }

    public static PopulationDataStorage getInstance() {
        if (instance == null) {
            instance = new PopulationDataStorage();
        }
        return instance;
    }

    public ArrayList<PopulationData> getPopulationList() {
        return populationList;
    }

    public void addPopulationData(PopulationData populationData) {
        populationList.add(populationData);
    }

    public String getMunicipality() {
        return municipality;
    }

    public void setMunicipality(String municipality) {
        this.municipality = municipality;
    }
}
