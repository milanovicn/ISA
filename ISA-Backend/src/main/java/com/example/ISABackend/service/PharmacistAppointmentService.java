package com.example.ISABackend.service;

import com.example.ISABackend.dto.DermatologistAppointmentDTO;
import com.example.ISABackend.dto.PharmacistAppointmentDTO;
import com.example.ISABackend.model.DermatologistAppointment;
import com.example.ISABackend.model.Pharmacist;
import com.example.ISABackend.model.PharmacistAppointment;
import com.example.ISABackend.model.Pharmacy;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public interface PharmacistAppointmentService {
    public List<PharmacistAppointment> getAll();
    public PharmacistAppointment getById(Long id);
    public ArrayList<PharmacistAppointment> getByPharmacist(Long pharmacistId);
    public ArrayList<PharmacistAppointment> getByPharmacy(Long pharmacyId);
    public PharmacistAppointment addPharmacistAppointment(Long pharmacyId, Long pharmacistId, String appointmentTime, Long price, LocalDate appointmentDate);
    public boolean isAvailable(Long dermatologistId, LocalDate appointmentDate, String appointmentTime);
    public ArrayList<PharmacistAppointmentDTO> getAvailableInPharmacy(Long pharmacyId);
    public PharmacistAppointment makeReservation(Long userId, Long appointmentId);
    public ArrayList<DermatologistAppointmentDTO> getAvailablePharmacistAppointments(Long pharmacyId);
    public ArrayList<Pharmacy> getPharmaciesByAppointmentDate(String appointmentTime, LocalDate appointmentDate);
    public ArrayList<DermatologistAppointmentDTO>  getByPatientId(Long patientId);
    public PharmacistAppointment cancelReservation(Long appointmentId);
    public ArrayList<Pharmacist> getPharmacistsForPatient(Long patientId);
    public ArrayList<DermatologistAppointmentDTO> getUnratedPharmacistsAppointments(Long patientId);
    public ArrayList<PharmacistAppointmentDTO> getReservedAppointments(Long id);
    public ArrayList<PharmacistAppointmentDTO> getAvailableAppointments(Long id);
    public PharmacistAppointmentDTO getDTOById(Long appointmentId);
    public PharmacistAppointment appointmentReserveForUser(Long appointmentId, Long patientId);

}
