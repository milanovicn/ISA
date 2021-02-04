package com.example.ISABackend.repository;

import com.example.ISABackend.model.DermatologistSchedule;
import com.example.ISABackend.model.PharmacistSchedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;

public interface PharmacistScheduleRepository extends JpaRepository<PharmacistSchedule, Long> {
    ArrayList<PharmacistSchedule> findByPharmacistId(Long pharmacistId);
}
