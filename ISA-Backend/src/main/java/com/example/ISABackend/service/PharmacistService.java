package com.example.ISABackend.service;

import com.example.ISABackend.model.Pharmacist;

public interface PharmacistService {

    public Pharmacist getByEmail(String email);
    public Pharmacist getById(Long id);
    public Pharmacist changePassword(Long id, String newPassword);
    public Pharmacist updatePharmacist(Pharmacist updatePharmacist);
}
