package com.example.oo_ohjelmointi_projekti;

public class PopulationData {
    private int year;
    private int amount;
    private int populationIncrease;
    public PopulationData(int year, int amount, int populationIncrease) {
        this.year = year;
        this.amount = amount;
        this.populationIncrease = populationIncrease;
    }
    public int getYear() {
        return year;
    }
    public int getAmount() {
        return amount;
    }
    public int getPopulationIncrease() {
        return populationIncrease;
    }
}
