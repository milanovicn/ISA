package com.example.ISABackend.model;

import com.example.ISABackend.enums.AppointmentStatus;
import com.example.ISABackend.enums.VacationStatus;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class PharmacistVacation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "pharmacistId", nullable = false)
    private Long pharmacistId;


    @Column(name = "dateFrom", nullable = false)
    private LocalDate dateFrom;

    @Column(name = "dateTo", nullable = false)
    private LocalDate dateTo;



    @Column(name = "status", nullable = false)
    private VacationStatus status;

    public PharmacistVacation() {}

    public PharmacistVacation(Long pharmacistId, LocalDate dateFrom, LocalDate dateTo, VacationStatus status)
    {
        this.pharmacistId = pharmacistId;

        this.dateFrom=dateFrom;
        this.dateTo = dateTo;
        this.status = status;
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

    public VacationStatus getStatus() {
        return status;
    }

    public void setStatus(VacationStatus status) {
        this.status = status;
    }
}
