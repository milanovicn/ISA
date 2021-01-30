package com.example.ISABackend.service;

import com.example.ISABackend.model.Dermatologist;
import com.example.ISABackend.model.Medicine;
import com.example.ISABackend.model.User;

import java.util.List;

public interface DermatologistService {
    public Dermatologist getById(Long id);
    public List<Dermatologist> getAll();
}
