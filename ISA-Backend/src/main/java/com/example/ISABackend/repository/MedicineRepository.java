package com.example.ISABackend.repository;

import com.example.ISABackend.model.Medicine;
import com.example.ISABackend.model.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicineRepository extends JpaRepository<Medicine, Long> {
    Medicine findByCode(int code);
    Medicine findByName(String name);
}
