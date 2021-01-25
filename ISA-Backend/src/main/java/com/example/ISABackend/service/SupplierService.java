package com.example.ISABackend.service;

import com.example.ISABackend.model.Supplier;
import com.example.ISABackend.model.SystemAdmin;
import com.example.ISABackend.model.User;

public interface SupplierService {
    public Supplier getByEmail(String email);
    public Supplier getById(Long id);
    public Supplier changePassword(Long id, String newPassword);
    public Supplier updateSupplier(Supplier updatedSupplier);
}
