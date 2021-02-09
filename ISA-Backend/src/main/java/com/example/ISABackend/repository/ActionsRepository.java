package com.example.ISABackend.repository;

import com.example.ISABackend.model.Actions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface ActionsRepository extends JpaRepository<Actions, Long> {
    ArrayList<Actions> findByPharmacyId(Long pharmacyId);

}
