package com.example.ISABackend.model;

import com.example.ISABackend.enums.OrderOfferStatus;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class OrderOffer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "orderId", nullable = false)
    private Long orderId;

    @Column(name = "supplierId", nullable = false)
    private Long supplierId;

    @Column(name = "price", nullable = false)
    private Long price;

    @Column(name = "deliveryDate", nullable = false)
    private LocalDate deliveryDate;


    @Column(name = "status", nullable = false)
    private OrderOfferStatus status;

    public OrderOffer() {
    }


    public OrderOffer(Long orderId, Long supplierId, Long price, LocalDate deliveryDate) {
        this.orderId = orderId;
        this.supplierId = supplierId;
        this.price = price;
        this.deliveryDate = deliveryDate;
        this.status = OrderOfferStatus.PENDING;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Long supplierId) {
        this.supplierId = supplierId;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public LocalDate getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(LocalDate deliveryDate) {
        this.deliveryDate = deliveryDate;
    }


    public OrderOfferStatus getStatus() {
        return status;
    }

    public void setStatus(OrderOfferStatus status) {
        this.status = status;
    }
}
