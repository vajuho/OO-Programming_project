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

    public String getTemperature() {
        return temperature;
    }
}
