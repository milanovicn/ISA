package com.example.ISABackend.model;

import com.example.ISABackend.enums.VacationStatus;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class DermatologistVacation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "dermatologistId", nullable = false)
    private Long dermatologistId;

    @Column(name = "pharmacyId", nullable = false)
    private Long pharmacyId;

    @Column(name = "dateFrom", nullable = false)
    private LocalDate dateFrom;

    @Column(name = "dateTo", nullable = false)
    private LocalDate dateTo;



    @Column(name = "status", nullable = false)
    private VacationStatus status;

    public DermatologistVacation() {}

    public DermatologistVacation(Long dermatologistId, Long pharmacyId, LocalDate dateFrom, LocalDate dateTo, VacationStatus status)
    {
        this.dermatologistId = dermatologistId;
        this.pharmacyId=pharmacyId;
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
