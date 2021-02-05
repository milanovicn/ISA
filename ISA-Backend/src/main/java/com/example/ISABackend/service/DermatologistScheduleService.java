package com.example.ISABackend.service;

import com.example.ISABackend.model.Dermatologist;
import com.example.ISABackend.model.DermatologistSchedule;

import java.util.ArrayList;
import java.util.List;

public interface DermatologistScheduleService {
    public List<DermatologistSchedule> getAll();
    public ArrayList<DermatologistSchedule> getFindByDermatologist(Long dermatologistId);
    public ArrayList<DermatologistSchedule> getByDermatologistAndPharmacy(Long dermatologistId, Long pharmacyId);
    public ArrayList<DermatologistSchedule> getByDermatologistAndPharmacyAndDay(Long dermatologistId, Long pharmacyId, String day);
    public DermatologistSchedule getById(Long id);
}
