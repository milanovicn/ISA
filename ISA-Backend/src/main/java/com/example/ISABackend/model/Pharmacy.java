package com.example.ISABackend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Pharmacy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "city", nullable = false)
    private String city;

    @Column(name = "rate", nullable = false)
    private double rate;

    @JsonIgnore
    @OneToMany(mappedBy = "pharmacy", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Pharmacy_Admin> pharmacistAdmin = new HashSet<Pharmacy_Admin>();

    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Pharmacist> pharmacist = new HashSet<Pharmacist>();

    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Dermatologist> dermatologist = new HashSet<Dermatologist>();

    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Medicine> medicine = new HashSet<Medicine>();

    public Pharmacy(String name, String description, String address, String city, double rate, Set<Pharmacy_Admin> pharmacistAdmin, Set<Pharmacist> pharmacist, Set<Dermatologist> dermatologist, Set<Medicine> medicine) {
        this.name = name;
        this.description = description;
        this.address = address;
        this.city = city;
        this.rate = rate;
        this.pharmacistAdmin = pharmacistAdmin;
        this.pharmacist = pharmacist;
        this.dermatologist = dermatologist;
        this.medicine = medicine;
    }

    public Set<Medicine> getMedicine() {
        return medicine;
    }

    public void setMedicine(Set<Medicine> medicine) {
        this.medicine = medicine;
    }

    public Pharmacy() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public Set<Pharmacist> getPharmacist() {
        return pharmacist;
    }

    public void setPharmacist(Set<Pharmacist> pharmacist) {
        this.pharmacist = pharmacist;
    }

    public Set<Dermatologist> getDermatologist() {
        return dermatologist;
    }

    public void setDermatologist(Set<Dermatologist> dermatologist) {
        this.dermatologist = dermatologist;
    }

    public Set<Pharmacy_Admin> getPharmacistAdmin() {
        return pharmacistAdmin;
    }

    public void setPharmacistAdmin(Set<Pharmacy_Admin> pharmacistAdmin) {
        this.pharmacistAdmin = pharmacistAdmin;
    }
}
