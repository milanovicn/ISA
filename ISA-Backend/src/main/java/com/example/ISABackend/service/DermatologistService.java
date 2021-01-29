package com.example.ISABackend.service;

import com.example.ISABackend.model.Dermatologist;

public interface DermatologistService {

    public Dermatologist getByEmail(String email);
    public Dermatologist getById(Long id);
    public Dermatologist changePassword(Long id, String newPassword);
    public Dermatologist updateDermatologist(Dermatologist updateDermatologist);
}
