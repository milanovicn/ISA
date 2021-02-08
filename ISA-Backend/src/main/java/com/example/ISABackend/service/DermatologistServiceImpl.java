package com.example.ISABackend.service;


import com.example.ISABackend.model.Dermatologist;
import com.example.ISABackend.model.DermatologistAppointment;
import com.example.ISABackend.model.User;
import com.example.ISABackend.repository.DermatologistRepository;
import com.example.ISABackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Service
public class DermatologistServiceImpl implements DermatologistService{

    @Autowired
    DermatologistRepository dermatologistRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    DermatologistAppointmentService dermatologistAppointmentService;


    @Override
    public List<Dermatologist> getAll() {
        return dermatologistRepository.findAll();
    }

    @Override
    public List<User> getAllUsers(Long dermatologistId) {

        ArrayList<User> ret = new ArrayList<User>();

       List<DermatologistAppointment> byDermatologist = dermatologistAppointmentService.getByDermatologist(dermatologistId);

       for (DermatologistAppointment i: byDermatologist) {

           User user = userRepository.findById(i.getPatientId()).orElseGet(null);

           if(!ret.contains(user)) {
               ret.add(user);
           }

       }

        return ret;
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
    public ArrayList<User> searchUser (String firstname, String lastname, Long dermatologistId) {


        ArrayList<User> ret = new ArrayList<User>();
        // getting all patients
        for (User u : getAllUsers(dermatologistId)){
            ret.add(u);
        }


        for (User u : getAllUsers(dermatologistId)){

            if (!u.getFirstName().toLowerCase().contains(firstname.toLowerCase()) ||
                    !u.getLastName().toLowerCase().equalsIgnoreCase(lastname.toLowerCase())) {
                ret.remove(u);
            }
        }

        System.out.println("RET : " + ret);
        return ret;
    }

}
