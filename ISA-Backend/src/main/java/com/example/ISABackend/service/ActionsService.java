package com.example.ISABackend.service;

import com.example.ISABackend.model.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public interface ActionsService {
    public Actions getById(Long id);
    public List<Actions> getAll();
    public Actions addNewAction(Actions newAction, Long pharmacyId);
    public ArrayList<Actions> getByPharmacy(Long pharmacyId);
    public ArrayList<Actions> getPharmacyActions(Long pharmacyId);


    public ArrayList<Pharmacy> getPharmaciesByPatientId(Long patientId);
    public Boolean isSubscribed(Long patientId, Long pharmacyId);
    public Boolean subscribeToAction(Long patientId, Long pharmacyId);
    public ActionPharmacyUser getByPharmacyAndUser(Long patientId, Long pharmacyId);
}
