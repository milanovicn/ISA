package com.example.ISABackend.service;

import com.example.ISABackend.model.Supplier;
import com.example.ISABackend.model.SystemAdmin;
import com.example.ISABackend.model.User;
import com.example.ISABackend.repository.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SupplierServiceImpl implements SupplierService {

    @Autowired
    SupplierRepository supplierRepository;


    @Override
    public Supplier getByEmail(String email) {
        return supplierRepository.findByEmail(email);
    }

    @Override
    public Supplier getById(Long id) {
        return supplierRepository.findById(id).orElseGet(null);
    }

    @Override
    public Supplier changePassword(Long id, String newPassword) {
        Supplier s =  getById(id);
        s.setPassword(newPassword);
        //false znaci da je promenio sifru
        //true znaci da mora da promeni sifru
        s.setPrviPutLogovan(false);
        s = supplierRepository.save(s);
        return s;
    }

    @Override
    public Supplier updateSupplier(Supplier updatedSupplier) {
        Supplier fromRepository =  getByEmail(updatedSupplier.getEmail());

        fromRepository.setAddress(updatedSupplier.getAddress());
        fromRepository.setFirstName(updatedSupplier.getFirstName());
        fromRepository.setLastName(updatedSupplier.getLastName());
        fromRepository.setCity(updatedSupplier.getCity());
        fromRepository.setCountry(updatedSupplier.getCountry());
        fromRepository.setPhoneNumber(updatedSupplier.getPhoneNumber());
        fromRepository.setPassword(updatedSupplier.getPassword());

        supplierRepository.save(fromRepository);

        return fromRepository;
    }
}
