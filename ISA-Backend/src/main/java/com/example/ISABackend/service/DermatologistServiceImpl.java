package com.example.ISABackend.service;

import com.example.ISABackend.enums.UserRole;
import com.example.ISABackend.model.*;
import com.example.ISABackend.repository.DermatologistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class DermatologistServiceImpl implements DermatologistService{

    @Autowired
    DermatologistRepository dermatologistRepository;

    @Autowired
    PharmacyService pharmacyService;

    @Override
    public List<Dermatologist> getAll() {
        return dermatologistRepository.findAll();
    }
  
    @Override
    public Dermatologist getByEmail(String email) {
        return dermatologistRepository.findByEmail(email);
    }

    @Override
    public Dermatologist getById(Long id) {
        return dermatologistRepository.findById(id).orElseGet(null);
    }

    @Override
    public Dermatologist changePassword(Long id, String newPassword) {
        Dermatologist d = getById(id);
        d.setPassword(newPassword);
        d.setPrviPutLogovan(false); //promenjena sifra
        d = dermatologistRepository.save(d);
        return d;
    }

    @Override
    public Dermatologist updateDermatologist(Dermatologist updateDermatologist) {


        Dermatologist forChange = getByEmail(updateDermatologist.getEmail());

        forChange.setAddress(updateDermatologist.getAddress());
        forChange.setFirstName(updateDermatologist.getFirstName());
        forChange.setLastName(updateDermatologist.getLastName());
        forChange.setCity(updateDermatologist.getCity());
        forChange.setCountry(updateDermatologist.getCountry());
        forChange.setPhoneNumber(updateDermatologist.getPhoneNumber());
        forChange.setPassword(updateDermatologist.getPassword());

        dermatologistRepository.save(forChange);

        return forChange;

    }




    }

