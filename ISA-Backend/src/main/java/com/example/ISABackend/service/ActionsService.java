package com.example.ISABackend.service;

import com.example.ISABackend.model.Actions;
import com.example.ISABackend.model.DermatologistAppointment;
import com.example.ISABackend.model.Medicine;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public interface ActionsService {
    public Actions getById(Long id);
    public List<Actions> getAll();
    public Actions addNewAction(Actions newAction, Long pharmacyId);
    public ArrayList<Actions> getByPharmacy(Long pharmacyId);
    public ArrayList<Actions> getPharmacyActions(Long pharmacyId);
}
