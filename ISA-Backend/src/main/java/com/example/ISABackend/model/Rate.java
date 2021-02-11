package com.example.ISABackend.model;

import com.example.ISABackend.enums.RatedEntity;
import com.example.ISABackend.enums.WorkDays;

import javax.persistence.*;

@Entity
public class Rate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ratedId", nullable = false)
    private Long ratedId;

    @Column(name = "reservationId", nullable = false)
    private Long reservationId;

    @Column(name = "patientId", nullable = false)
    private Long patientId;

    @Column(name = "rate", nullable = false)
    private double rate;

    @Column(name = "ratedType", nullable = false)
    private RatedEntity ratedType;

    @Column(name = "ratedSubject", nullable = false)
    private String ratedSubject;


    public Rate() {
    }

    public Rate(Long ratedId, Long patientId, double rate, RatedEntity ratedType, String ratedSubject, Long reservationId) {
        this.ratedId = ratedId;
        this.patientId = patientId;
        this.rate = rate;
        this.ratedType = ratedType;
        this.ratedSubject = ratedSubject;
        this.reservationId = reservationId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRatedId() {
        return ratedId;
    }

    public void setRatedId(Long ratedId) {
        this.ratedId = ratedId;
    }

    public Long getPatientId() {
        return patientId;
    }

    public void setPatientId(Long pharmacyId) {
        this.patientId = pharmacyId;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public RatedEntity getRatedType() {
        return ratedType;
    }

    public void setRatedType(RatedEntity ratedType) {
        this.ratedType = ratedType;
    }

    public String getRatedSubject() {
        return ratedSubject;
    }

    public void setRatedSubject(String ratedSubject) {
        this.ratedSubject = ratedSubject;
    }

    public Long getReservationId() {
        return reservationId;
    }

    public void setReservationId(Long reservationId) {
        this.reservationId = reservationId;
    }
}
