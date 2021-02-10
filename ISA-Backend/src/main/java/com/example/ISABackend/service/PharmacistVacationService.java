package com.example.ISABackend.service;

import com.example.ISABackend.model.PharmacistVacation;

import java.util.ArrayList;
import java.util.List;

public interface PharmacistVacationService {
    public PharmacistVacation getById(Long id);
    public List<PharmacistVacation> getAll();

    public PharmacistVacation addVacation(PharmacistVacation newVacation, Long id);
    public ArrayList<PharmacistVacation> getVacations(Long pharmacistId);
}
