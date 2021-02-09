package com.example.ISABackend.service;

import com.example.ISABackend.dto.SearchPharmacy;
import com.example.ISABackend.enums.WorkDays;
import com.example.ISABackend.model.Dermatologist;
import com.example.ISABackend.model.Medicine;
import com.example.ISABackend.model.Pharmacist;
import com.example.ISABackend.model.Pharmacy;

import java.util.ArrayList;
import java.util.Collection;

public interface PharmacyService {
    public Pharmacy getById(Long id);
    public Collection<Pharmacy> findAll();
    public ArrayList<Pharmacy> search(SearchPharmacy searchParameters);
    public ArrayList<Pharmacy>  sort(ArrayList<Pharmacy> sortPharmacies, String sortType);
    public Pharmacy updatePharmacy(Pharmacy updatedPharmacy);
    public Pharmacy addMedicine(Long pharmacy_id, Long medicine_id);
    public Long deleteMedicine(Long pharmacy_id, Long medicine_id);
    public Pharmacy addNew(Pharmacy newPharmacy);
    
    public ArrayList<Dermatologist> getDermatologists(Long pharmacyId);
    public ArrayList<Dermatologist> getAvailableDermatologists(Long pharmacyId);
    public ArrayList<Pharmacist> getPharmacists(Long pharmacyId);
    public ArrayList<Pharmacist> getAvailablePharmacists(Long pharmacyId);
    public Long scheduleDermatologist(Long pharmacyId, Long dermatologistId, ArrayList<WorkDays> workDays);
    public Long schedulePharmacist(Long pharmacyId, Long pharmacistId, ArrayList<WorkDays> workDays);


}
