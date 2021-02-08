package com.example.ISABackend.repository;

import com.example.ISABackend.model.DermatologistAppointment;
import com.example.ISABackend.model.MedicineReservation;
import com.example.ISABackend.model.Pharmacy;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;

public interface MedicineReservationRepository extends JpaRepository<MedicineReservation, Long> {

     ArrayList<MedicineReservation> findByMedicineId(Long medicineId);
     ArrayList<MedicineReservation> findByPharmacyId(Long pharmacyId);
     ArrayList<MedicineReservation> findByPatientId(Long patientId);

}
