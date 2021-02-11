package com.example.ISABackend.service;

import com.example.ISABackend.dto.PharmacistAppointmentDTO;
import com.example.ISABackend.model.Dermatologist;
import com.example.ISABackend.model.Pharmacist;
import com.example.ISABackend.model.User;

import java.util.ArrayList;
import java.util.List;

public interface PharmacistService {

    public Pharmacist getByEmail(String email);
    public Pharmacist getById(Long id);
    public Pharmacist changePassword(Long id, String newPassword);
    public Pharmacist updatePharmacist(Pharmacist updatePharmacist);
    public Pharmacist addNew(Pharmacist newPharma, Long pharmacyId);
    public List<Pharmacist> getAll();
    public Long delete(Long id);
    public List<User> getAllUsers(Long pharmacistId);
    public ArrayList<User> searchUser(String firstname, String lastname, Long pharmacistId);
}
