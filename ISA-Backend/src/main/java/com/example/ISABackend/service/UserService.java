package com.example.ISABackend.service;

import com.example.ISABackend.model.User;

import java.util.Collection;

public interface UserService {
    public Collection<User> findAll();
    public User getById(Long id);
    public User getByEmail(String email);
}
