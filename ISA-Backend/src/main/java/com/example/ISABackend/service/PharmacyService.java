package com.example.ISABackend.service;

import com.example.ISABackend.dto.SearchPharmacy;
import com.example.ISABackend.model.Pharmacy;
import com.example.ISABackend.model.Pharmacy_Admin;

import java.util.ArrayList;
import java.util.Collection;

public interface PharmacyService {
    public Pharmacy getById(Long id);
    public Collection<Pharmacy> findAll();
    public ArrayList<Pharmacy> search(SearchPharmacy searchParameters);
    public ArrayList<Pharmacy>  sort(ArrayList<Pharmacy> sortPharmacies, String sortType);
    public Pharmacy updatePharmacy(Pharmacy updatedPharmacy);

}
