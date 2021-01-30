package com.example.ISABackend.repository;


import com.example.ISABackend.model.DermatologistSchedule;
import com.example.ISABackend.model.PharmacyStock;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;

public interface PharmacyStockRepository extends JpaRepository<PharmacyStock, Long> {
    ArrayList<PharmacyStock> findByPharmacyId(Long pharmacyId);
    ArrayList<PharmacyStock> findByMedicineId(Long medicineId);

}
