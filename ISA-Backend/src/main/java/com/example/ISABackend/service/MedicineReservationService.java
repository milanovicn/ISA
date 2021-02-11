package com.example.ISABackend.service;

import com.example.ISABackend.dto.DermatologistAppointmentDTO;
import com.example.ISABackend.model.DermatologistAppointment;
import com.example.ISABackend.model.MedicineReservation;
import com.example.ISABackend.model.Pharmacy;
import com.example.ISABackend.model.Rate;

import java.util.ArrayList;
import java.util.List;

public interface MedicineReservationService {

    public List<MedicineReservation> getAll();
    public MedicineReservation getById(Long id);
    public ArrayList<MedicineReservation> getByMedicineId(Long medicineId);
    public ArrayList<MedicineReservation> getByPharmacyId(Long pharmacyId);
    public ArrayList<MedicineReservation> getByPatientId(Long patientId);
    public MedicineReservation makeReservation(MedicineReservation newReservation);
    public MedicineReservation cancelReservation(Long id);
    public ArrayList<Pharmacy> getPharmaciesWithAvailableMedicine(Long medicineId);
    public ArrayList<Pharmacy> getPharmaciesForPatient(Long patientId);
    public MedicineReservation checkMedicineReservationCode(String reservationCode);
    public MedicineReservation issueMedicineReservation(Long reservationId);
    public ArrayList<MedicineReservation> getUnratedMedicines(Long patientId);
    public ArrayList<MedicineReservation> getUnratedPharmacies(Long patientId);

}
