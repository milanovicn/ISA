package com.example.ISABackend.service;

import com.example.ISABackend.model.Dermatologist;
import com.example.ISABackend.model.Pharmacist;

import java.util.List;

public interface PharmacistService {

    public Pharmacist getByEmail(String email);
    public Pharmacist getById(Long id);
    public Pharmacist changePassword(Long id, String newPassword);
    public Pharmacist updatePharmacist(Pharmacist updatePharmacist);
    public Pharmacist addNew(Pharmacist newPharma, Long pharmacyId);
    public List<Pharmacist> getAll();
}
