package com.example.oo_ohjelmointi_projekti;
public class WeatherData {
    private String name;
    private String main;
    private String description;
    private String temperature;

    public WeatherData(String name, String main, String description, String temp) {
        this.name = name;
        this.main = main;
        this.description = description;
        this.temperature = temp;
    }
    public String getName() {
        return name;
    }

    public String getMain() {
        return main;
    }

    public String getDescription() {
        return description;
    }

    public double getTemperature() {
        double temperatureKelvin = Double.parseDouble(temperature);
        double temperatureCelsius = temperatureKelvin - 273.15;
        return temperatureCelsius;
    }
    //Tämä on testi
}
