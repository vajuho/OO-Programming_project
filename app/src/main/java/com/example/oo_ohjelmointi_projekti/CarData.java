package com.example.oo_ohjelmointi_projekti;

public class CarData {
    private String carAmount;
    private int year;

    public CarData(String carAmount, int year){
            this.carAmount = carAmount;
            this.year = year;
    }

    public String getCarAmount() {
        return carAmount;
    }

    public void setCarAmount(String carAmount) {
        this.carAmount = carAmount;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
