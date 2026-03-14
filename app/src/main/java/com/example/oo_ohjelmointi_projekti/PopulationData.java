package com.example.oo_ohjelmointi_projekti;

public class PopulationData {
    private int year;
    private int amount;
    public PopulationData(int year, int amount) {
        this.year = year;
        this.amount = amount;
    }
    public int getYear() {
        return year;
    }
    public int getAmount() {
        return amount;
    }
}
