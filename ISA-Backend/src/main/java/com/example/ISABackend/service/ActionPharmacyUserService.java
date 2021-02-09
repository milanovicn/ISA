package com.example.ISABackend.service;

import com.example.ISABackend.model.ActionPharmacyUser;

import java.util.ArrayList;

public interface ActionPharmacyUserService {
    public ArrayList<ActionPharmacyUser> getByUser(Long userId);
    public ArrayList<ActionPharmacyUser> getByPharmacy(Long pharmacyId);
}
