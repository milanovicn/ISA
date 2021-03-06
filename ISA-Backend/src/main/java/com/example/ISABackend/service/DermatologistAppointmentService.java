package com.example.ISABackend.service;

import com.example.ISABackend.dto.DermatologistAppointmentDTO;
import com.example.ISABackend.model.Dermatologist;
import com.example.ISABackend.model.DermatologistAppointment;
import com.example.ISABackend.model.DermatologistSchedule;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public interface DermatologistAppointmentService {

    public List<DermatologistAppointment> getAll();
    public DermatologistAppointment getById(Long id);
    public ArrayList<DermatologistAppointment> getByDermatologist(Long dermatologistId);
    public ArrayList<DermatologistAppointment> getByPharmacy(Long pharmacyId);
    public DermatologistAppointment addDermatologistAppointment(Long pharmacyId, Long dermatologistId, String appointmentTime, Long price, LocalDate appointmentDate);
    public boolean isAvailable(Long dermatologistId, LocalDate appointmentDate, String appointmentTime);
    public ArrayList<DermatologistAppointmentDTO> getAvailableDermatologistAppointments(Long pharmacyId);
    public DermatologistAppointment makeReservation(Long userId, Long appointmentId);
    public ArrayList<DermatologistAppointmentDTO> getReservedAppointments(Long id);
    public ArrayList<DermatologistAppointmentDTO> getAvailableAppointments(Long id);
    public DermatologistAppointmentDTO getDTOById(Long appointmentId);
    public DermatologistAppointment appointmentReserveForUser(Long appointmentId, Long patientId);
    public ArrayList<DermatologistAppointmentDTO> getByPatientId(Long patientId);
    public DermatologistAppointment cancelReservation(Long appointmentId);
    public ArrayList<Dermatologist> getDermatologistsForPatient(Long patientId);
    public ArrayList<DermatologistAppointmentDTO> getUnratedDermatologistsAppointments(Long patientId);
    public ArrayList<DermatologistAppointmentDTO> getReservedAndDoneAppointments(Long id);
    public DermatologistAppointment didntShowUp(Long appointmentId);
}
