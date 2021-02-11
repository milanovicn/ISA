package com.example.ISABackend.model;

import com.example.ISABackend.enums.AppointmentStatus;
import com.example.ISABackend.enums.WorkDays;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class DermatologistAppointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "dermatologistId", nullable = false)
    private Long dermatologistId;

    @Column(name = "pharmacyId", nullable = false)
    private Long pharmacyId;

    @Column(name = "patientId", nullable = false)
    private Long patientId;

    @Column(name = "time", nullable = false)
    private String time;

    @Column(name = "date", nullable = false)
    private LocalDate date;

    @Column(name = "price", nullable = false)
    private double price;

    @Column(name = "status", nullable = false)
    private AppointmentStatus status;

    @Column(name = "rated", nullable = false)
    private boolean rated;

    public DermatologistAppointment() {
    }

    public DermatologistAppointment(Long dermatologistId, Long pharmacyId, Long patientId, String time, LocalDate date,
                                    double price, AppointmentStatus status, boolean rated) {
        this.dermatologistId = dermatologistId;
        this.pharmacyId = pharmacyId;
        this.patientId = patientId;
        this.time = time;
        this.date = date;
        this.price = price;
        this.status = status;
        this.rated = rated;
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

    public Long getPatientId() {
        return patientId;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public AppointmentStatus getStatus() {
        return status;
    }

    public void setStatus(AppointmentStatus status) {
        this.status = status;
    }

    public boolean isRated() {
        return rated;
    }

    public void setRated(boolean rated) {
        this.rated = rated;
    }
}
