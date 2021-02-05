package com.example.ISABackend.service;

import com.example.ISABackend.enums.AppointmentStatus;
import com.example.ISABackend.enums.UserRole;
import com.example.ISABackend.model.*;
import com.example.ISABackend.repository.DermatologistAppointmentRepository;
import com.example.ISABackend.repository.DermatologistRepository;
import com.example.ISABackend.repository.DermatologistScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class DermatologistServiceImpl implements DermatologistService{

    @Autowired
    DermatologistRepository dermatologistRepository;

    @Autowired
    DermatologistAppointmentService dermatologistAppointmentService;

    @Autowired
    DermatologistAppointmentRepository dermatologistAppointmentRepository;

    @Autowired
    PharmacyService pharmacyService;

    @Autowired
    DermatologistScheduleRepository dermatologistScheduleRepository;

    @Autowired
    DermatologistScheduleService dermatologistScheduleService;
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
    @Override
    public Long delete(Long id) {

        List<DermatologistAppointment> pa = dermatologistAppointmentService.getByPharmacy(id);
        for( DermatologistAppointment prolazim : pa) {
            if(prolazim.getStatus().equals(AppointmentStatus.RESERVED)){
                return null;
            }

        }
        DermatologistSchedule p1 = dermatologistScheduleService.getById(id);
        p1.setPharmacyId((long) 0);
        dermatologistScheduleRepository.save(p1);
        for(DermatologistAppointment prolazim : pa)
        {
            prolazim.setStatus(AppointmentStatus.ENDED);
            dermatologistAppointmentRepository.save(prolazim);
        }

        return p1.getId();

    }



    }

