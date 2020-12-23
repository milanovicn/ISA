package com.example.ISABackend.service;

import com.example.ISABackend.model.Pharmacy;
import com.example.ISABackend.model.Pharmacy_Admin;
import com.example.ISABackend.model.User;
import com.example.ISABackend.repository.PharmacyAdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

@Service
public class PharmacyAdminServiceImpl implements PharmacyAdminService {
    @Autowired
    PharmacyAdminRepository pharmacyAdminRepository;

    public Pharmacy_Admin getById(Long id) {
        return pharmacyAdminRepository.findById(id).orElseGet(null);  }

    @Override
    public Pharmacy_Admin getByEmail(String email) {
        return pharmacyAdminRepository.findByEmail(email);
    }

    @Override
    public Collection<Pharmacy_Admin> findAll() {
        Collection<Pharmacy_Admin> result = pharmacyAdminRepository.findAll();
        return result;
    }
    @Override
    public Pharmacy_Admin promeniLozinku(Long idAdmin, String noviPassword) {
        Pharmacy_Admin adminZaPromenu =  getById(idAdmin);
        adminZaPromenu.setPassword(noviPassword);
        adminZaPromenu.setPrviPutLogovan(false);
        adminZaPromenu = pharmacyAdminRepository.save(adminZaPromenu);
        return adminZaPromenu;
    }

    @Override
    public ArrayList<Pharmacy_Admin> findByIdPharmacy(Long idPharmacy) {
        ArrayList<Pharmacy_Admin> listaAll = (ArrayList<Pharmacy_Admin>) this.findAll();
        ArrayList<Pharmacy_Admin> listaByIdPharmacy = new ArrayList<Pharmacy_Admin>();
        for (Pharmacy_Admin pa:listaAll ) {
            if(pa.getPharmacy().getId()== idPharmacy){
                listaByIdPharmacy.add(pa);

            }

        }
        return listaByIdPharmacy;
    }
    @Override

    public Pharmacy getPharmacyByAdminId(Long adminId)
    {
        Pharmacy_Admin pa = pharmacyAdminRepository.getOne(adminId);
        return pa.getPharmacy();
    }
@Override
    public Pharmacy_Admin updateAdmin(Pharmacy_Admin updatedAdmin) {
        Pharmacy_Admin fromRepository =  getByEmail(updatedAdmin.getEmail());

        fromRepository.setAddress(updatedAdmin.getAddress());
        fromRepository.setFirstName(updatedAdmin.getFirstName());
        fromRepository.setLastName(updatedAdmin.getLastName());
        fromRepository.setCity(updatedAdmin.getCity());
        fromRepository.setCountry(updatedAdmin.getCountry());
        fromRepository.setPhoneNumber(updatedAdmin.getPhoneNumber());
        fromRepository.setPassword(updatedAdmin.getPassword());

        pharmacyAdminRepository.save(fromRepository);

        return fromRepository;
    }

  //  public Pharmacy_Admin getByIdP(Long id) {
       // return pharmacyAdminRepository.findByIdPharmacy(id);
   // }


}
