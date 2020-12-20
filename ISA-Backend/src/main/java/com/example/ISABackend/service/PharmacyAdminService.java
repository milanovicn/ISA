package com.example.ISABackend.service;

import com.example.ISABackend.model.Pharmacy_Admin;
import com.example.ISABackend.model.User;

public interface PharmacyAdminService {
    public Pharmacy_Admin getById(Long id);
    public Pharmacy_Admin getByEmail(String email);
}
