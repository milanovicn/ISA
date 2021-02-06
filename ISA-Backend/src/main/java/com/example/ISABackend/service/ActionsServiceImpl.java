package com.example.ISABackend.service;

import com.example.ISABackend.model.*;
import com.example.ISABackend.repository.ActionsRepository;
import com.example.ISABackend.repository.MedicineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class ActionsServiceImpl implements  ActionsService {

    @Autowired
    private ActionsRepository actionsRepository;

    @Override
    public Actions getById(Long id){
        return  actionsRepository.findById(id).orElseGet(null);
    }

    @Override
    public List<Actions> getAll(){
        return actionsRepository.findAll();
    }

    @Override
    public ArrayList<Actions> getByPharmacy(Long pharmacyId){
        return actionsRepository.findByPharmacyId(pharmacyId);
    }

    @Override
    public Actions addNewAction(Actions newAction, Long pharmacyId){

            Actions a = new Actions();
            a.setPharmacyId(pharmacyId);
            a.setDateFrom(newAction.getDateFrom().plusDays(1));
            a.setDateTo(newAction.getDateTo().plusDays(1));
            a.setDescription(newAction.getDescription());
            actionsRepository.save(a);
            return a;

        }


    public ArrayList<Actions> getPharmacyActions(Long pharmacyId){
        ArrayList<Actions> akcije = this.getByPharmacy(pharmacyId);

        return akcije;
    }





    }

