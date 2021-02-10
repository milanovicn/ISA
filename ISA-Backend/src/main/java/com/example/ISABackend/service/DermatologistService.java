package com.example.ISABackend.service;
import com.example.ISABackend.model.Dermatologist;
import com.example.ISABackend.model.User;

import java.util.ArrayList;
import java.util.List;

public interface DermatologistService  {
    public List<Dermatologist> getAll();
    public Dermatologist getByEmail(String email);
    public Dermatologist getById(Long id);
    public Dermatologist changePassword(Long id, String newPassword);
    public Dermatologist updateDermatologist(Dermatologist updateDermatologist);
    public List<User> getAllUsers(Long dermatologistId);
    public ArrayList<User> searchUser (String firstname, String lastname, Long dermatologistId);
    public Long delete(Long id, Long pharmacyId);

}
