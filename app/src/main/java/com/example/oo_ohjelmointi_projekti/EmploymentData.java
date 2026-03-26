package com.example.oo_ohjelmointi_projekti;

public class EmploymentData {
    private String employmentRate;

    private String employmentSelfSufficiency;

    public EmploymentData(String employmentRate, String employmentSelfSufficiency) {
        this.employmentRate = employmentRate;
        this.employmentSelfSufficiency = employmentSelfSufficiency;
    }

    public String getEmploymentRate() {
        return employmentRate;
    }

    public String getEmploymentSelfSufficiency() {
        return employmentSelfSufficiency;
    }
}
