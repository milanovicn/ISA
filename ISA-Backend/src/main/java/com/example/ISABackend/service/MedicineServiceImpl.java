package com.example.ISABackend.service;

import com.example.ISABackend.model.Medicine;
import com.example.ISABackend.repository.MedicineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicineServiceImpl implements MedicineService {

    @Autowired
    private MedicineRepository medicineRepository;

    @Override
    public Medicine getById(Long id) {
        return  medicineRepository.findById(id).orElseGet(null);
    }

    @Override
    public List<Medicine> getAll() {
        return medicineRepository.findAll();
    }


}
