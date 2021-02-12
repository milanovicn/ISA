package com.example.ISABackend.repository;

import com.example.ISABackend.model.DermatologistAppointment;
import com.example.ISABackend.model.MedicineReservation;
import com.example.ISABackend.model.Pharmacy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.query.Param;

import javax.persistence.LockModeType;
import javax.persistence.QueryHint;
import java.util.ArrayList;

public interface MedicineReservationRepository extends JpaRepository<MedicineReservation, Long> {

     ArrayList<MedicineReservation> findByMedicineId(Long medicineId);
     ArrayList<MedicineReservation> findByPharmacyId(Long pharmacyId);
     ArrayList<MedicineReservation> findByPatientId(Long patientId);

     @Lock(LockModeType.PESSIMISTIC_WRITE)
     @QueryHints({@QueryHint(name = "javax.persistence.lock.timeout", value ="0")})
     @Query("select mr.id from MedicineReservation mr where mr.id = :id")
     public MedicineReservation findOneById(@Param("id")Long id);

}
