package com.example.ISABackend.model;

import com.example.ISABackend.enums.WorkDays;

import javax.persistence.*;

@Entity
public class PharmacistSchedule {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "pharmacistId", nullable = false)
    private Long pharmacistId;

    @Column(name = "pharmacyId", nullable = false)
    private Long pharmacyId;

    @Column(name = "workDay", nullable = false)
    private WorkDays workDay;

    public PharmacistSchedule() {
    }

    public PharmacistSchedule(Long pharmacistId, Long pharmacyId, WorkDays workDay) {
        this.pharmacistId = pharmacistId;
        this.pharmacyId = pharmacyId;
        this.workDay = workDay;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPharmacistId() {
        return pharmacistId;
    }

    public void setPharmacistId(Long pharmacistId) {
        this.pharmacistId = pharmacistId;
    }

    public Long getPharmacyId() {
        return pharmacyId;
    }

    public void setPharmacyId(Long pharmacyId) {
        this.pharmacyId = pharmacyId;
    }

    public WorkDays getWorkDay() {
        return workDay;
    }

    public void setWorkDay(WorkDays workDay) {
        this.workDay = workDay;
    }
}
