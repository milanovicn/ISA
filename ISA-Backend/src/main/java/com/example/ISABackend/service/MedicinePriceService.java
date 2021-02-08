package com.example.ISABackend.service;

import com.example.ISABackend.dto.DateInterval;
import com.example.ISABackend.model.Actions;
import com.example.ISABackend.model.MedicinePrice;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public interface MedicinePriceService {
    public MedicinePrice getById(Long id);
    public List<MedicinePrice> getAll();
    public MedicinePrice addNewPrice(Long pharmacyId, Long medicineId, DateInterval dateInterval, Long price);
    public ArrayList<MedicinePrice> getByPharmacy(Long pharmacyId);
    public ArrayList<MedicinePrice> getMedicinePrice(Long pharmacyId);
    public ArrayList<MedicinePrice> getByMedicineAndPharmacy(Long medicineId, Long pharmacyId);
}
