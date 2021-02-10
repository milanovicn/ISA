package com.example.ISABackend.repository;

import com.example.ISABackend.model.DermatologistVacation;
import com.example.ISABackend.model.PharmacistSchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface DermatologistVacationRepository extends JpaRepository<DermatologistVacation, Long> {
    ArrayList<DermatologistVacation> findByDermatologistId(Long dermatologistId);
}
