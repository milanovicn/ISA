package com.example.ISABackend.service;

import com.example.ISABackend.model.DermatologistSchedule;
import com.example.ISABackend.model.PharmacistSchedule;

import java.util.ArrayList;
import java.util.List;

public interface PharmacistScheduleService {


    public List<PharmacistSchedule> getAll();
    public ArrayList<PharmacistSchedule> getFindByPharmacistLong(Long pharmacistId);
    public ArrayList<PharmacistSchedule> getByPharmacistAndPharmacy(Long pharmacistId, Long pharmacyId);
    public ArrayList<PharmacistSchedule> getByPharmacistAndPharmacyAndDay(Long pharmacistId, Long pharmacyId, String day);
    public boolean workInPharmacy(Long pharmacistId, Long pharmacyId);
}
