package com.example.ISABackend.service;

import com.example.ISABackend.enums.UserRole;
import com.example.ISABackend.model.*;
import com.example.ISABackend.repository.DermatologistRepository;
import com.example.ISABackend.repository.PharmacistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PharmacistServiceImpl implements PharmacistService {

    @Autowired
    PharmacistRepository pharmacistRepository;



    @Autowired
    PharmacyService pharmacyService;

    @Override
    public List<Pharmacist> getAll() {
        return pharmacistRepository.findAll();
    }

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

    @Override
    public Pharmacist addNew(Pharmacist newPharma, Long pharmacyId) {
        Pharmacy forNewPharma = pharmacyService.getById(pharmacyId);
        if (getByEmail(newPharma.getEmail()) == null) {
            Pharmacist p = new Pharmacist();
            p.setPassword(newPharma.getPassword());
            p.setFirstName(newPharma.getFirstName());
            p.setLastName(newPharma.getLastName());
            p.setAddress(newPharma.getAddress());
            p.setCity(newPharma.getCity());
            p.setCountry(newPharma.getCountry());
            p.setPhoneNumber(newPharma.getPhoneNumber());
            p.setEmail(newPharma.getEmail());
            p.setPrviPutLogovan(true);
            p.setUserRole(UserRole.PHARMACIST);
            p.setPharmacy(forNewPharma);

            pharmacistRepository.save(p);
            return p;
        } else {
            return null;
        }
    }


}


