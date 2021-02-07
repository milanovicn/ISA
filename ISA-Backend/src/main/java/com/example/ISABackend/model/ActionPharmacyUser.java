package com.example.ISABackend.model;

import javax.persistence.*;

@Entity
public class ActionPharmacyUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "pharmacyId", nullable = false)
    private Long pharmacyId;

    @Column(name = "userId", nullable = false)
    private Long userId;

    public ActionPharmacyUser(Long pharmacyId, Long userId){
        this.pharmacyId = pharmacyId;
        this.userId = userId;
    }

    public ActionPharmacyUser() {}

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

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
