package com.example.ISABackend.service;

import com.example.ISABackend.model.Pharmacy_Admin;
import com.example.ISABackend.model.User;
import com.example.ISABackend.repository.PharmacyAdminRepository;
import org.apache.catalina.Store;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;


public interface PharmacyAdminService {
    public Pharmacy_Admin getById(Long id);
    public Pharmacy_Admin getByEmail(String email);


    public Collection<Pharmacy_Admin> findAll();
    public  Pharmacy_Admin promeniLozinku(Long idAdmin, String noviPassword);
    public ArrayList<Pharmacy_Admin> findByIdPharmacy(Long idPharmacy);



//findbyid = getbyid
//    @Override
//    public Pharmacy_Admin findById(Long id) {
//        Pharmacy_Admin ret = adminRepository.findById(id).orElseGet(null);
//        return ret;
//    }
//        public Pharmacy_Admin create(Pharmacy_Admin adminPharmacy) throws Exception {
//        Pharmacy_Admin ret = new Pharmacy_Admin();
//
//        adminPharmacy.setPrviPutLogovan(true);
//        ret.copyValues(adminPharmacy);
//        ret = adminRepository.save(ret);
//    return ret;



   // public default Pharmacy_Admin findByEmail(String email) {
        //Pharmacy_Admin ret = adminRepository.findByEmail(email);
       // return ret;
    //}


}