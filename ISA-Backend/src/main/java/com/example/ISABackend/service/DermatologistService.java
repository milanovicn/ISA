package com.example.ISABackend.service;

import com.example.ISABackend.model.Dermatologist;

import java.util.List;

public interface DermatologistService {
   
    public interface DermatologistService {
    public List<Dermatologist> getAll();
    public Dermatologist getByEmail(String email);
    public Dermatologist getById(Long id);
    public Dermatologist changePassword(Long id, String newPassword);
    public Dermatologist updateDermatologist(Dermatologist updateDermatologist);

}
