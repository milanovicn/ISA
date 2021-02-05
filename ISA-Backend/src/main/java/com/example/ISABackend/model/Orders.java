package com.example.ISABackend.model;

import com.example.ISABackend.enums.OrderStatus;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "pharmacyId", nullable = false)
    private Long pharmacyId;

    @Column(name = "pharmacyName", nullable = false)
    private String pharmacyName;

    @Column(name = "pharmacyAdminId", nullable = false)
    private Long pharmacyAdminId;

    @Column(name = "deadline", nullable = false)
    private LocalDate deadline;

    @Column(name = "orderStatus", nullable = false)
    private OrderStatus orderStatus;

    public Orders() {
    }

    public Orders(Long pharmacyId, String pharmacyName, Long pharmacyAdminId, LocalDate deadline, OrderStatus orderStatus) {
        this.pharmacyId = pharmacyId;
        this.pharmacyName = pharmacyName;
        this.pharmacyAdminId = pharmacyAdminId;
        this.deadline = deadline;
        this.orderStatus = orderStatus;
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

    public String getPharmacyName() {
        return pharmacyName;
    }

    public void setPharmacyName(String pharmacyName) {
        this.pharmacyName = pharmacyName;
    }

    public Long getPharmacyAdminId() {
        return pharmacyAdminId;
    }

    public void setPharmacyAdminId(Long pharmacyAdminId) {
        this.pharmacyAdminId = pharmacyAdminId;
    }

    public LocalDate getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDate deadline) {
        this.deadline = deadline;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }
}
