package com.example.ISABackend.service;

import com.example.ISABackend.model.Pharmacy_Admin;
import com.example.ISABackend.model.SystemAdmin;
import com.example.ISABackend.repository.SystemAdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SystemAdminServiceImpl implements SystemAdminService {

    @Autowired
    SystemAdminRepository systemAdminRepository;


    @Override
    public SystemAdmin getByEmail(String email) {
        return systemAdminRepository.findByEmail(email);
    }

    @Override
    public SystemAdmin getById(Long id) {
        return systemAdminRepository.findById(id).orElseGet(null);
    }

    @Override
    public SystemAdmin changePassword(Long id, String newPassword) {
        SystemAdmin sa =  getById(id);
        sa.setPassword(newPassword);
        //false znaci da je promenio sifru
        //true znaci da mora da promeni sifru
        sa.setPrviPutLogovan(false);
        sa = systemAdminRepository.save(sa);
        return sa;
    }
}
