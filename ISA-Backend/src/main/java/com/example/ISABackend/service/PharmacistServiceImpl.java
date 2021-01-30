package com.example.ISABackend.service;

import com.example.ISABackend.model.Pharmacist;
import com.example.ISABackend.repository.PharmacistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PharmacistServiceImpl implements PharmacistService {

    @Autowired
    PharmacistRepository pharmacistRepository;

    @Override
    public Pharmacist getByEmail(String email) {
        return pharmacistRepository.findByEmail(email);
    }

    @Override
    public Pharmacist getById(Long id) {
        return pharmacistRepository.findById(id).orElseGet(null);
    }

    @Override
    public Pharmacist changePassword(Long id, String newPassword) {
        Pharmacist p = getById(id);
        p.setPassword(newPassword);
        p.setPrviPutLogovan(false); //promenjena sifra
        p = pharmacistRepository.save(p);
        return p;
    }


    @Override
    public Pharmacist updatePharmacist(Pharmacist updatePharmacist) {

        Pharmacist forChange = getByEmail(updatePharmacist.getEmail());

        forChange.setAddress(updatePharmacist.getAddress());
        forChange.setFirstName(updatePharmacist.getFirstName());
        forChange.setLastName(updatePharmacist.getLastName());
        forChange.setCity(updatePharmacist.getCity());
        forChange.setCountry(updatePharmacist.getCountry());
        forChange.setPhoneNumber(updatePharmacist.getPhoneNumber());
        forChange.setPassword(updatePharmacist.getPassword());

        pharmacistRepository.save(forChange);

        return forChange;
    }
}
