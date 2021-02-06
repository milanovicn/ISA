package com.example.ISABackend.service;

import com.example.ISABackend.model.Dermatologist;
import com.example.ISABackend.model.Medicine;
import com.example.ISABackend.model.PharmacyStock;

import java.util.ArrayList;
import java.util.List;

public interface PharmacyStockService {

    public PharmacyStock getById(Long id);
    public List<PharmacyStock> getAll();
    public  ArrayList<PharmacyStock> getByPharmacyId(Long id);
    public ArrayList<PharmacyStock> getByMedicineId(Long id);
    public Long addNewMedicineInStock(Long pharmacyId, Long medicineId, int quantity);
    public ArrayList<Medicine> getMedicineInStock(Long pharmacyId);
    public ArrayList<PharmacyStock> getPharmacyStock(Long pharmacyId);
    public void addMedicinesFromOrder(Long orderId);
}
