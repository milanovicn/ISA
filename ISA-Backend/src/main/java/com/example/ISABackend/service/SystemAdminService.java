package com.example.ISABackend.service;

import com.example.ISABackend.model.SystemAdmin;
import com.example.ISABackend.model.User;

public interface SystemAdminService {
    public SystemAdmin getByEmail(String email);
    public SystemAdmin getById(Long id);
    public SystemAdmin changePassword(Long id, String newPassword);
}
