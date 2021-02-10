package com.example.ISABackend.repository;

import com.example.ISABackend.model.PharmacistSchedule;
import com.example.ISABackend.model.PharmacistVacation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface PharmacistVacationRepository extends JpaRepository<PharmacistVacation, Long> {

    ArrayList<PharmacistVacation> findByPharmacistId(Long pharmacistId);
}
