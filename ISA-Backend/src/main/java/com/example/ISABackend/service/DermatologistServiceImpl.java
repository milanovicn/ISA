package com.example.ISABackend.service;

import com.example.ISABackend.model.Dermatologist;
import com.example.ISABackend.model.User;
import com.example.ISABackend.repository.DermatologistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DermatologistServiceImpl implements DermatologistService{

    @Autowired
    private DermatologistRepository dermatologistRepository;

    @Override
    public Dermatologist getById(Long id) {
        return  dermatologistRepository.findById(id).orElseGet(null);
    }

    @Override
    public List<Dermatologist> getAll() {
        return dermatologistRepository.findAll();
    }
}
