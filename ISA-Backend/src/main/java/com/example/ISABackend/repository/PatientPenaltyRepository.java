package com.example.ISABackend.repository;

import com.example.ISABackend.model.MedicineReservation;
import com.example.ISABackend.model.PatientPenalty;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;

public interface PatientPenaltyRepository extends JpaRepository<PatientPenalty,Long> {


    ArrayList<PatientPenalty> findByPatientId(Long patientId);
}
