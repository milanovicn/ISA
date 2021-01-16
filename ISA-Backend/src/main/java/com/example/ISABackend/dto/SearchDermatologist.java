package com.example.ISABackend.dto;

public class SearchDermatologist {
    private String firstname;
    private String lastname;
    private String email;
    private String address;
    private String city;
    private double rateTo;
    private double rateFrom;

    public SearchDermatologist() {
    }

    public SearchDermatologist(String firstname, String lastname, String email, String address, String city, double rateTo, double rateFrom) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.address = address;
        this.city = city;
        this.rateTo = rateTo;
        this.rateFrom = rateFrom;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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