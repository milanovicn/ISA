package com.example.ISABackend.service;

import com.example.ISABackend.dto.DermatologistAppointmentDTO;
import com.example.ISABackend.model.Medicine;
import com.example.ISABackend.model.Pharmacy;
import com.example.ISABackend.model.User;

import java.util.ArrayList;
import java.util.Collection;

public interface UserService {
    public Collection<User> findAll();
    public User getById(Long id);
    public User getByEmail(String email);
    public User updateUser(User updatedUser);
    public User addAllergy(Long user_id, Long medicine_id);
    public User registerUser(User newUser);
    public ArrayList<DermatologistAppointmentDTO> sort(ArrayList<DermatologistAppointmentDTO> sortAppointments, String sortType);
    public Long getMyPenalty( Long patientId);
    public ArrayList<Medicine> getNonAlergyMedicine(Long patientId);
    public Long addPenalty (Long patientId);
}
