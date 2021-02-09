package com.example.ISABackend.repository;

import com.example.ISABackend.model.ActionPharmacyUser;
import com.example.ISABackend.model.Actions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface ActionPharmacyUserRepository extends JpaRepository<ActionPharmacyUser, Long> {
    ArrayList<ActionPharmacyUser> findByUserId(Long userId);

    ArrayList<ActionPharmacyUser> findByPharmacyId(Long pharmacyId);

}
