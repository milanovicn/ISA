package com.example.ISABackend.repository;

import com.example.ISABackend.model.ActionPharmacyUser;
import com.example.ISABackend.model.MedicinePrice;
import jdk.jfr.Registered;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface MedicinePriceRepository extends JpaRepository<MedicinePrice, Long> {


    ArrayList<MedicinePrice> findByPharmacyId(Long pharmacyId);
}
