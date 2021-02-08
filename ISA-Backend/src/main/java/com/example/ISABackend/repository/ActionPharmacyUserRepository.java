package com.example.ISABackend.repository;

import com.example.ISABackend.model.ActionPharmacyUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;

public interface ActionPharmacyUserRepository extends JpaRepository<ActionPharmacyUser, Long> {
    ArrayList<ActionPharmacyUser> findByPharmacyId(Long pharmacyId);
    ArrayList<ActionPharmacyUser> findByUserId(Long userId);
}
