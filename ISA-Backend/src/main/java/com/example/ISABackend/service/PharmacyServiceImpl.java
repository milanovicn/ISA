package com.example.ISABackend.service;

import com.example.ISABackend.model.Pharmacy;
import com.example.ISABackend.repository.PharmacyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PharmacyServiceImpl implements PharmacyService {
    @Autowired
    private PharmacyRepository pharmacyRepository;


    @Override
    public Pharmacy getById(Long id) {
      return  pharmacyRepository.findById(id).orElseGet(null);
    }

}
