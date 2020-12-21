package com.example.ISABackend.model;

import com.example.ISABackend.enums.UserRole;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Pharmacy_Admin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "firstName", nullable = false)
    private String firstName;

    @Column(name = "lastName", nullable = false)
    private String lastName;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "phoneNumber", nullable = false)
    private String phoneNumber;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "city", nullable = false)
    private String city;

    @Column(name = "country", nullable = false)
    private String country;

    @Column(name = "userRole", nullable = false)
    private UserRole userRole = UserRole.PHARMACY_ADMIN;

    @Column(name = "PrviPutLogovan", nullable = false)
    private boolean prviPutLogovan;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Pharmacy pharmacy;


    public Pharmacy_Admin(String firstName, String lastName, String email, String phoneNumber, String password, String address, String city, String country, UserRole userRole, Pharmacy pharmacy, boolean prviPutLogovan) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.address = address;
        this.city = city;
        this.country = country;
        this.userRole = userRole;
        this.pharmacy = pharmacy;
        this.prviPutLogovan = prviPutLogovan;
    }


    public Pharmacy_Admin() {
    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public String getAddress() {
        return address;
    }

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }

    public UserRole getUserRole() {
        return userRole;
    }

    public Pharmacy getPharmacy() {
        return pharmacy;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }

    public void setPharmacy(Pharmacy pharmacy) {
        this.pharmacy = pharmacy;
    }


    public boolean isPrviPutLogovan() {
        return prviPutLogovan;
    }

    public void setPrviPutLogovan(boolean prviPutLogovan) {
        this.prviPutLogovan = prviPutLogovan;
    }

   //  Pharmacy_Admin create(Pharmacy_Admin pharmacy_admin) throws Exception;
    //  CREATE NEMAM NIKAD


    public void copyValues(Pharmacy_Admin pharmacyAdmin) {
        this.firstName = pharmacyAdmin.getFirstName();
        this.lastName = pharmacyAdmin.getLastName();
        this.email = pharmacyAdmin.getEmail();
        this.password = pharmacyAdmin.getPassword();
        this.city = pharmacyAdmin.getCity();
        this.country = pharmacyAdmin.getCountry();
        this.address = pharmacyAdmin.getAddress();
        this.phoneNumber = pharmacyAdmin.getPhoneNumber();
        this.pharmacy = pharmacyAdmin.getPharmacy();
        this.userRole = pharmacyAdmin.getUserRole();
        this.prviPutLogovan = pharmacyAdmin.isPrviPutLogovan();
    }

}