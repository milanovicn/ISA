package com.example.ISABackend.service;

import com.example.ISABackend.model.Pharmacy;
import com.example.ISABackend.model.Pharmacy_Admin;

public interface PharmacyService {
    public Pharmacy getById(Long id);
    public Pharmacy updatePharmacy(Pharmacy updatedPharmacy);
}
