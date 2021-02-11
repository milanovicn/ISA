package com.example.ISABackend.dto;

import com.example.ISABackend.enums.AppointmentStatus;

import java.time.LocalDate;

public class PharmacistAppointmentDTO {
    private Long appointmentId;
    private Long pharmacistId;
    private String pharmacistName;
    private double pharmacistRate;
    private Long pharmacyId;
    private String pharmacyName;
    private String time;
    private LocalDate date;
    private double price;
    private String patientName;
    private Long patientId;
    private AppointmentStatus appointmentStatus;

    public PharmacistAppointmentDTO() {
    }

    public PharmacistAppointmentDTO(Long appointmentId, Long pharmacistId, String pharmacistName, double pharmacistRate,
                                    Long pharmacyId, String pharmacyName, String time, LocalDate date, double price,
                                    String patientName, Long patientId, AppointmentStatus appointmentStatus) {
        this.appointmentId = appointmentId;
        this.pharmacistId = pharmacistId;
        this.pharmacistName = pharmacistName;
        this.pharmacistRate = pharmacistRate;
        this.pharmacyId = pharmacyId;
        this.pharmacyName = pharmacyName;
        this.time = time;
        this.date = date;
        this.price = price;
        this.patientName = patientName;
        this.patientId = patientId;
        this.appointmentStatus = appointmentStatus;
    }

    public Long getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(Long appointmentId) {
        this.appointmentId = appointmentId;
    }

    public Long getPharmacistId() {
        return pharmacistId;
    }

    public void setPharmacistId(Long pharmacistId) {
        this.pharmacistId = pharmacistId;
    }

    public String getPharmacistName() {
        return pharmacistName;
    }

    public void setPharmacistName(String pharmacistName) {
        this.pharmacistName = pharmacistName;
    }

    public double getPharmacistRate() {
        return pharmacistRate;
    }

    public void setPharmacistRate(double pharmacistRate) {
        this.pharmacistRate = pharmacistRate;
    }

    public Long getPharmacyId() {
        return pharmacyId;
    }

    public void setPharmacyId(Long pharmacyId) {
        this.pharmacyId = pharmacyId;
    }

    public String getPharmacyName() {
        return pharmacyName;
    }

    public void setPharmacyName(String pharmacyName) {
        this.pharmacyName = pharmacyName;
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

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public Long getPatientId() {
        return patientId;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }

    public AppointmentStatus getAppointmentStatus() {
        return appointmentStatus;
    }

    public void setAppointmentStatus(AppointmentStatus appointmentStatus) {
        this.appointmentStatus = appointmentStatus;
    }
}
