package com.example.ISABackend.service;

import com.example.ISABackend.model.*;
import com.example.ISABackend.repository.ActionPharmacyUserRepository;
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

    @Autowired
    private ActionPharmacyUserRepository actionPharmacyUserRepository;

    @Autowired
    private PharmacyService pharmacyService;

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

    @Override
    public ArrayList<Pharmacy> getPharmaciesByPatientId(Long patientId) {
        ArrayList<ActionPharmacyUser> byPatient = actionPharmacyUserRepository.findByUserId(patientId);
        ArrayList<Pharmacy> ret = new ArrayList<Pharmacy>();
        for(ActionPharmacyUser a : byPatient){
            ret.add(pharmacyService.getById(a.getPharmacyId()));
        }

        return ret;
    }

    @Override
    public Boolean isSubscribed(Long patientId, Long pharmacyId) {
        List<ActionPharmacyUser> all = actionPharmacyUserRepository.findAll();
        for(ActionPharmacyUser action : all){
            if(action.getPharmacyId() == pharmacyId && action.getUserId()==patientId){
                return true;
            }
        }

        return false;
    }

    //ako je subskrajbovan desubskrajbuje ga, ako nije onda pravi novi subskrajb
    @Override
    public Boolean subscribeToAction(Long patientId, Long pharmacyId) {
        //ako je subskrajbovan ukidam subskrajb fizicki lagano
        if(isSubscribed(patientId,pharmacyId)){
            ActionPharmacyUser toUnsubscribe = getByPharmacyAndUser(patientId, pharmacyId);
            //a malo i logicki
            toUnsubscribe.setUserId((long) 0);
            actionPharmacyUserRepository.delete(toUnsubscribe);

        } else {
            //ako nije subskrajbovan onda pravim subskripciju
            ActionPharmacyUser newSubscription = new ActionPharmacyUser();
            newSubscription.setUserId(patientId);
            newSubscription.setPharmacyId(pharmacyId);
            actionPharmacyUserRepository.save(newSubscription);

        }
        //vracam novo stanje
        return isSubscribed(patientId,pharmacyId);
    }

    @Override
    public ActionPharmacyUser getByPharmacyAndUser(Long patientId, Long pharmacyId) {
        for(ActionPharmacyUser action : actionPharmacyUserRepository.findAll()){
            if(action.getPharmacyId() == pharmacyId && action.getUserId()==patientId){
                return action;
            }
        }
        return null;
    }


}

