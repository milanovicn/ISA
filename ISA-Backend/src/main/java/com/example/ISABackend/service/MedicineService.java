package com.example.ISABackend.service;

import com.example.ISABackend.model.Medicine;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

public interface MedicineService {

    public Medicine getById(Long id);
    public List<Medicine> getAll();
}
