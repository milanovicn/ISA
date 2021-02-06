package com.example.ISABackend.repository;

import com.example.ISABackend.model.Medicine;
import com.example.ISABackend.model.Pharmacy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;


public interface PharmacyRepository  extends JpaRepository<Pharmacy, Long> {
 //   ArrayList<Medicine> findByMedicineId(Long id);
}
