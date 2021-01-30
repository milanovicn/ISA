package com.example.ISABackend.model;

import com.example.ISABackend.enums.WorkDays;

import javax.persistence.*;

@Entity
public class DermatologistSchedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "dermatologistId", nullable = false)
    private Long dermatologistId;

    @Column(name = "pharmacyId", nullable = false)
    private Long pharmacyId;

    @Column(name = "workDay", nullable = false)
    private WorkDays workDay;

    public DermatologistSchedule() {
    }

    public DermatologistSchedule(Long dermatologistId, Long pharmacyId, WorkDays workDay) {
        this.dermatologistId = dermatologistId;
        this.pharmacyId = pharmacyId;
        this.workDay = workDay;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getDermatologistId() {
        return dermatologistId;
    }

    public void setDermatologistId(Long dermatologistId) {
        this.dermatologistId = dermatologistId;
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
