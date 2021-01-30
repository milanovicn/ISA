package com.example.ISABackend.model;

import javax.persistence.*;

@Entity
public class PharmacyStock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "pharmacyId", nullable = false)
    private Long pharmacyId;

    @Column(name = "medicineId", nullable = false)
    private Long medicineId;

    @Column(name = "medicineName", nullable = false)
    private String medicineName;

    @Column(name = "inStock", nullable = false)
    private int inStock;

    @Column(name = "reserved", nullable = false)
    private int reserved;

    public PharmacyStock() {
    }

    public PharmacyStock(Long pharmacyId, Long medicineId, int inStock, int reserved, String medicineName) {
        this.pharmacyId = pharmacyId;
        this.medicineId = medicineId;
        this.inStock = inStock;
        this.reserved = reserved;
        this.medicineName = medicineName;
    }

    public String getMedicineName() {
        return medicineName;
    }

    public void setMedicineName(String medicineName) {
        this.medicineName = medicineName;
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

    public Long getMedicineId() {
        return medicineId;
    }

    public void setMedicineId(Long medicineId) {
        this.medicineId = medicineId;
    }

    public int getInStock() {
        return inStock;
    }

    public void setInStock(int inStock) {
        this.inStock = inStock;
    }

    public int getReserved() {
        return reserved;
    }

    public void setReserved(int reserved) {
        this.reserved = reserved;
    }
}
