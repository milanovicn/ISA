package com.example.ISABackend.model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Actions {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "pharmacyId", nullable = false)
    private Long pharmacyId;

    @Column(name = "dateFrom", nullable = false)
    private LocalDate dateFrom;

    @Column(name = "dateTo", nullable = false)
    private LocalDate dateTo;

    @Column(name = "description", nullable = false)
    private String description;

    public Actions(Long pharmacyId, LocalDate dateFrom, LocalDate dateTo, String description){
        this.pharmacyId = pharmacyId;
        this.dateFrom=dateFrom;
        this.dateTo=dateTo;
        this.description=description;
    }
    public Actions() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPharmacyId() {
        return pharmacyId;
    }

    public void setPharmacyId(Long pharmacyId) {
        this.pharmacyId = pharmacyId;
    }

    public LocalDate getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(LocalDate dateFrom) {
        this.dateFrom = dateFrom;
    }

    public LocalDate getDateTo() {
        return dateTo;
    }

    public void setDateTo(LocalDate dateTo) {
        this.dateTo = dateTo;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
