package com.example.ISABackend.repository;

import com.example.ISABackend.model.DermatologistAppointment;
import com.example.ISABackend.model.PharmacistAppointment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;

public interface PharmacistAppointmentRepository extends JpaRepository<PharmacistAppointment, Long> {
    ArrayList<PharmacistAppointment> findByPharmacistId(Long pharmacistId);
    ArrayList<PharmacistAppointment> findByPharmacyId(Long pharmacistId);
    ArrayList<PharmacistAppointment> findByPatientId(Long patientId);
}
