package com.example.ISABackend.model;

import com.example.ISABackend.enums.MedicineReservationStatus;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class MedicineReservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "medicineId", nullable = false)
    private Long medicineId;

    @Column(name = "medicineName", nullable = false)
    private String medicineName;

    @Column(name = "pharmacyId", nullable = false)
    private Long pharmacyId;

    @Column(name = "pharmacyName", nullable = false)
    private String pharmacyName;

    @Column(name = "patientId", nullable = false)
    private Long patientId;

    @Column(name = "patientEmail", nullable = false)
    private String patientEmail;

    @Column(name = "pickUpDate", nullable = false)
    private LocalDate pickUpDate;

    @Column(name = "status", nullable = false)
    private MedicineReservationStatus status;

    @Column(name = "pickedUp", nullable = false)
    private boolean pickedUp;

    public MedicineReservation(Long medicineId, String medicineName, Long pharmacyId, String pharmacyName, Long patientId,
                               String patientEmail, LocalDate pickUpDate, MedicineReservationStatus status, boolean pickedUp) {
        this.medicineId = medicineId;
        this.medicineName = medicineName;
        this.pharmacyId = pharmacyId;
        this.pharmacyName = pharmacyName;
        this.patientId = patientId;
        this.patientEmail = patientEmail;
        this.pickUpDate = pickUpDate;
        this.status = status;
        this.pickedUp = pickedUp;
    }

    public MedicineReservation() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMedicineId() {
        return medicineId;
    }

    public void setMedicineId(Long medicineId) {
        this.medicineId = medicineId;
    }

    public String getMedicineName() {
        return medicineName;
    }

    public void setMedicineName(String medicineName) {
        this.medicineName = medicineName;
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

    public Long getPatientId() {
        return patientId;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }

    public String getPatientEmail() {
        return patientEmail;
    }

    public void setPatientEmail(String patientEmail) {
        this.patientEmail = patientEmail;
    }

    public LocalDate getPickUpDate() {
        return pickUpDate;
    }

    public void setPickUpDate(LocalDate pickUpDate) {
        this.pickUpDate = pickUpDate;
    }

    public MedicineReservationStatus getStatus() {
        return status;
    }

    public void setStatus(MedicineReservationStatus status) {
        this.status = status;
    }

    public boolean isPickedUp() {
        return pickedUp;
    }

    public void setPickedUp(boolean pickedUp) {
        this.pickedUp = pickedUp;
    }
}
