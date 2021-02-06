package com.example.ISABackend.repository;

import com.example.ISABackend.model.DermatologistAppointment;
import com.example.ISABackend.model.DermatologistSchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

public interface DermatologistAppointmentRepository extends JpaRepository<DermatologistAppointment, Long> {
    ArrayList<DermatologistAppointment> findByDermatologistId(Long dermatologistId);
    ArrayList<DermatologistAppointment> findByPharmacyId(Long pharmacyId);
}
