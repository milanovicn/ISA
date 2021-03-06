package com.example.ISABackend.service;

import com.example.ISABackend.enums.AppointmentStatus;
import com.example.ISABackend.enums.UserRole;
import com.example.ISABackend.model.*;
import com.example.ISABackend.repository.DermatologistRepository;
import com.example.ISABackend.repository.PharmacistAppointmentRepository;
import com.example.ISABackend.repository.PharmacistRepository;
import com.example.ISABackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PharmacistServiceImpl implements PharmacistService {

    @Autowired
    PharmacistRepository pharmacistRepository;

    @Autowired
    PharmacistAppointmentRepository pharmacistAppointmentRepository;

    @Autowired
    PharmacistAppointmentService pharmacistAppointmentService;

    @Autowired
    PharmacyService pharmacyService;

    @Autowired
    UserRepository userRepository;

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
       // Pharmacy forNewPharma = pharmacyService.getById(pharmacyId);
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
            p.setPharmacyId(pharmacyId);
            pharmacistRepository.save(p);
            return p;
        } else {
            return null;
        }
    }

    @Override
    public Long delete(Long id) {

        List<PharmacistAppointment> pa = pharmacistAppointmentService.getByPharmacist(id);
        for( PharmacistAppointment prolazim : pa) {
            if(prolazim.getStatus().equals(AppointmentStatus.RESERVED)){

                return null;
            }

        }
        Pharmacist p1 = getById(id);
        p1.setPharmacyId((long) 0);
        pharmacistRepository.save(p1);
        for(PharmacistAppointment prolazim : pa)
        {
            prolazim.setStatus(AppointmentStatus.ENDED);
            pharmacistAppointmentRepository.save(prolazim);
        }

        return p1.getId();

    }

    @Override
    public List<User> getAllUsers(Long pharmacistId) {

        ArrayList<User> ret = new ArrayList<User>();

        List<PharmacistAppointment> byPharmacist = pharmacistAppointmentService.getByPharmacist(pharmacistId);

        for (PharmacistAppointment i: byPharmacist) {

            User user = userRepository.findById(i.getPatientId()).orElseGet(null);

            if(!ret.contains(user)) {
                ret.add(user);
            }

        }

        return ret;
    }

    @Override
    public ArrayList<User> searchUser(String firstname, String lastname, Long pharmacistId) {


        ArrayList<User> ret = new ArrayList<User>();
        // getting all patients
        for (User u : getAllUsers(pharmacistId)){
            ret.add(u);
        }


        for (User u : getAllUsers(pharmacistId)){

            if (!u.getFirstName().toLowerCase().contains(firstname.toLowerCase()) ||
                    !u.getLastName().toLowerCase().equalsIgnoreCase(lastname.toLowerCase())) {
                ret.remove(u);
            }
        }

        System.out.println("RET : " + ret);
        return ret;
    }

}


