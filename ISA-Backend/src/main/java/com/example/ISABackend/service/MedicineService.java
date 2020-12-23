package com.example.ISABackend.service;

import com.example.ISABackend.dto.SearchMedicine;
import com.example.ISABackend.dto.SearchPharmacy;
import com.example.ISABackend.model.Medicine;
import com.example.ISABackend.model.Pharmacy;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public interface MedicineService {

    public Medicine getById(Long id);
    public List<Medicine> getAll();
    public ArrayList<Medicine> search(SearchMedicine searchParameters);
}
