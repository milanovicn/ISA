package com.example.ISABackend.dto;

public class SearchPharmacy {
    private String name;
    private String description;
    private String address;
    private String city;
    private double rateTo;
    private double rateFrom;

    public SearchPharmacy() {
    }

    public SearchPharmacy(String name, String description, String address, String city, double rateTo, double rateFrom) {
        this.name = name;
        this.description = description;
        this.address = address;
        this.city = city;
        this.rateTo = rateTo;
        this.rateFrom = rateFrom;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public double getRateTo() {
        return rateTo;
    }

    public void setRateTo(double rateTo) {
        this.rateTo = rateTo;
    }

    public double getRateFrom() {
        return rateFrom;
    }

    public void setRateFrom(double rateFrom) {
        this.rateFrom = rateFrom;
    }
}
