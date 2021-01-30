package com.example.ISABackend.service;

import com.example.ISABackend.dto.SearchDermatologist;
import com.example.ISABackend.dto.SearchPharmacist;
import com.example.ISABackend.model.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;


public interface PharmacyAdminService {
    public Pharmacy_Admin getById(Long id);
    public Pharmacy_Admin getByEmail(String email);
    public ArrayList<Dermatologist> searchD(SearchDermatologist searchParameters);
    public ArrayList<Pharmacist> searchP(SearchPharmacist searchParameters);
    public Collection<Pharmacy_Admin> findAll();
    public  Pharmacy_Admin promeniLozinku(Long idAdmin, String noviPassword);
    public ArrayList<Pharmacy_Admin> findByIdPharmacy(Long idPharmacy);
    public Pharmacy_Admin updateAdmin(Pharmacy_Admin updatedAdmin);
    public Pharmacy getPharmacyByAdminId(Long adminId);
    public Pharmacy_Admin addNew(Pharmacy_Admin newPharmacyAdmin, Long pharmacyId);
    }