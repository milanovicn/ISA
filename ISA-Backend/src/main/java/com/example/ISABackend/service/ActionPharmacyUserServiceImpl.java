package com.example.ISABackend.service;

import com.example.ISABackend.model.ActionPharmacyUser;
import com.example.ISABackend.model.DermatologistAppointment;
import com.example.ISABackend.repository.ActionPharmacyUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ActionPharmacyUserServiceImpl implements  ActionPharmacyUserService{
    @Autowired
    ActionPharmacyUserRepository actionPharmacyUserRepository;


    public ArrayList<ActionPharmacyUser> getByUser(Long userId)
    {
        return actionPharmacyUserRepository.findByUserId(userId);
    }
    public ArrayList<ActionPharmacyUser> getByPharmacy(Long pharmacyId){
        return actionPharmacyUserRepository.findByPharmacyId(pharmacyId);
    }
}
