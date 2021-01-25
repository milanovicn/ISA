package com.example.ISABackend.repository;

import com.example.ISABackend.model.Supplier;
import com.example.ISABackend.model.SystemAdmin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier, Long> {
    Supplier findByEmail(String email);
}
