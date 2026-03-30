package com.example.oo_ohjelmointi_projekti;

public class PopulationData {
    private int year;
    private int populationAmount;
    private int populationIncrease;
    public PopulationData(int year, int populationAmount, int populationIncrease) {
        this.year = year;
        this.populationAmount = populationAmount;
        this.populationIncrease = populationIncrease;
    }
    public int getYear() {
        return year;
    }
    public int getAmount() {
        return populationAmount;
    }
    public int getPopulationIncrease() {
        return populationIncrease;
    }
}
