package com.example.ISABackend.service;

import com.example.ISABackend.enums.UserRole;
import com.example.ISABackend.model.Pharmacy_Admin;
import com.example.ISABackend.model.Supplier;
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

    @Override
    public SystemAdmin addNew(SystemAdmin newSystemAdmin) {
        if (getByEmail(newSystemAdmin.getEmail()) == null) {
            SystemAdmin s = new SystemAdmin();
            s.setPassword(newSystemAdmin.getPassword());
            s.setFirstName(newSystemAdmin.getFirstName());
            s.setLastName(newSystemAdmin.getLastName());
            s.setAddress(newSystemAdmin.getAddress());
            s.setCity(newSystemAdmin.getCity());
            s.setCountry(newSystemAdmin.getCountry());
            s.setPhoneNumber(newSystemAdmin.getPhoneNumber());
            s.setEmail(newSystemAdmin.getEmail());
            s.setPrviPutLogovan(true);
            s.setUserRole(UserRole.SYSTEM_ADMIN);

            systemAdminRepository.save(s);
            return s;
        } else {
            return null;
        }
    }
}
