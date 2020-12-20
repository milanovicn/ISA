package com.example.ISABackend.service;

import com.example.ISABackend.model.Pharmacy;
import com.example.ISABackend.model.Pharmacy_Admin;
import com.example.ISABackend.repository.PharmacyAdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PharmacyAdminServiceImpl implements PharmacyAdminService {
    @Autowired
    PharmacyAdminRepository pharmacyAdminRepository;

    @Override
    public Pharmacy_Admin getById(Long id) {
        return pharmacyAdminRepository.findById(id).orElseGet(null);

    }

    @Override
    public Pharmacy_Admin getByEmail(String email) {
        return pharmacyAdminRepository.findByEmail(email);
    }


}
