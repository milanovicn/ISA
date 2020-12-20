package com.example.ISABackend.repository;

import com.example.ISABackend.model.Pharmacy_Admin;
import com.example.ISABackend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PharmacyAdminRepository extends JpaRepository<Pharmacy_Admin, Long> {
    Pharmacy_Admin findByEmail(String email);
}
