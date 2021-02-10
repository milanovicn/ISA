package com.example.ISABackend.service;

import com.example.ISABackend.model.DermatologistVacation;
import com.example.ISABackend.model.PharmacistVacation;

import java.util.ArrayList;
import java.util.List;

public interface DermatologistVacationService {
    public DermatologistVacation getById(Long id);
    public List<DermatologistVacation> getAll();

    public DermatologistVacation addVacation(DermatologistVacation newVacation, Long id);
    public ArrayList<DermatologistVacation> getVacations(Long dermatologistId);
    public ArrayList<DermatologistVacation> getDermaVacationsForPharmacy(Long pharmacyId);
    public DermatologistVacation acceptVacation(Long idOdmora);
    public DermatologistVacation rejectVacation(Long idOdmora);
}
