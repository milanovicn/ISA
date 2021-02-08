package com.example.ISABackend.model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class MedicinePrice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "dateFrom", nullable = false)
    private LocalDate dateFrom;

    @Column(name = "dateTo", nullable = false)
    private LocalDate dateTo;

    @Column(name = "price", nullable = false)
    private Long price;

    @Column(name = "pharmacyId", nullable = false)
    private Long pharmacyId;

    @Column(name = "medicineId", nullable = false)
    private Long medicineId;


   public MedicinePrice(Long pharmacyId,Long medicineId, LocalDate dateFrom, LocalDate dateTo, Long price){
       this.pharmacyId=pharmacyId;
       this.medicineId=medicineId;
       this.dateFrom=dateFrom;
       this.dateTo=dateTo;
       this.price=price;


   }

   public MedicinePrice(){}

    public Long getPharmacyId() {
        return pharmacyId;
    }

    public Long getMedicineId() {
        return medicineId;
    }

    public void setMedicineId(Long medicineId) {
        this.medicineId = medicineId;
    }

    public void setPharmacyId(Long pharmacyId) {
        this.pharmacyId = pharmacyId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }
}
