package com.example.ISABackend.repository;

import com.example.ISABackend.model.Pharmacist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PharmacistRepository  extends JpaRepository<Pharmacist, Long> {
}
