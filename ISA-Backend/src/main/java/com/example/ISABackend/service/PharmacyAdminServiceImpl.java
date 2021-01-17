package com.example.ISABackend.service;

import com.example.ISABackend.dto.SearchDermatologist;
import com.example.ISABackend.dto.SearchPharmacist;
import com.example.ISABackend.model.Dermatologist;
import com.example.ISABackend.model.Pharmacist;
import com.example.ISABackend.model.Pharmacy;
import com.example.ISABackend.model.Pharmacy_Admin;
import com.example.ISABackend.repository.DermatologistRepository;
import com.example.ISABackend.repository.PharmacistRepository;
import com.example.ISABackend.repository.PharmacyAdminRepository;
import com.example.ISABackend.repository.PharmacyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

@Service
public class PharmacyAdminServiceImpl implements PharmacyAdminService {
    @Autowired
    PharmacyAdminRepository pharmacyAdminRepository;
    @Autowired
    DermatologistRepository dermatologistRepository;
    @Autowired
    PharmacistRepository pharmacistRepository;
    @Autowired
    PharmacyRepository pharmacyRepository;

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
  public ArrayList<Dermatologist> searchD(SearchDermatologist searchParameters) {

      ArrayList<Dermatologist> ret = new ArrayList<Dermatologist>();

      // getting all dermas
      for (Dermatologist p : dermatologistRepository.findAll()) {
          ret.add(p);
      }

      for (Dermatologist p : dermatologistRepository.findAll()) {

          // if derma address doesn't meet search condition
          if (!searchParameters.getAddress().equals("all")) {
              if (!p.getAddress().toLowerCase().contains(searchParameters.getAddress().toLowerCase())) {
                  // and it is in the ret list
                  if (ret.contains(p)) {
                      // remove it from the ret list
                      ret.remove(p);
                  }
              }

          }

          // if pharmacy name doesn't meet search condition
          if (!searchParameters.getFirstname().equals("all")) {
              if (!p.getFirstName().toLowerCase().contains(searchParameters.getFirstname().toLowerCase())) {
                  // and it is in the ret list
                  if (ret.contains(p)) {
                      // remove it from the ret list
                      ret.remove(p);
                  }
              }

          }
          // if  name doesn't meet search condition
          if (!searchParameters.getLastname().equals("all")) {
              if (!p.getLastName().toLowerCase().contains(searchParameters.getLastname().toLowerCase())) {
                  // and it is in the ret list
                  if (ret.contains(p)) {
                      // remove it from the ret list
                      ret.remove(p);
                  }
              }

          }

          // if derma's description doesn't meet search condition
          if (!searchParameters.getEmail().equals("all")) {
              if (!p.getEmail().toLowerCase().contains(searchParameters.getEmail().toLowerCase())) {
                  // and it is in the ret list
                  if (ret.contains(p)) {
                      // remove it from the ret list
                      ret.remove(p);
                  }
              }

          }

          // if derma's city doesn't meet search condition
          if (!searchParameters.getCity().equals("all")) {
              if (!p.getCity().toLowerCase().contains(searchParameters.getCity().toLowerCase())) {
                  // and it is in the ret list
                  if (ret.contains(p)) {
                      // remove it from the ret list
                      ret.remove(p);
                  }
              }

          }

          if (searchParameters.getRateTo() != 123456789 || searchParameters.getRateFrom() != -123456789) {
              if (!(p.getRate() <= searchParameters.getRateTo() && p.getRate() >= searchParameters.getRateFrom())) {
                  if (ret.contains(p)) {
                      ret.remove(p);
                  }
              }
          }



      }

      System.out.println("RET : " + ret);

      return ret;
  }

    public ArrayList<Pharmacist> searchP(SearchPharmacist searchParameters){

        ArrayList<Pharmacist> ret = new ArrayList<Pharmacist>();

        // getting all dermas
        for (Pharmacist p : pharmacistRepository.findAll())  {
            ret.add(p);
        }

        for (Pharmacist p : pharmacistRepository.findAll()) {

            // if derma address doesn't meet search condition
            if (!searchParameters.getAddress().equals("all")) {
                if (!p.getAddress().toLowerCase().contains(searchParameters.getAddress().toLowerCase())) {
                    // and it is in the ret list
                    if (ret.contains(p)) {
                        // remove it from the ret list
                        ret.remove(p);
                    }
                }
            }

            // if pharmacy name doesn't meet search condition
            if (!searchParameters.getFirstname().equals("all")) {
                if (!p.getFirstName().toLowerCase().contains(searchParameters.getFirstname().toLowerCase())) {
                    // and it is in the ret list
                    if (ret.contains(p)) {
                        // remove it from the ret list
                        ret.remove(p);
                    }
                }
            }
            // if  name doesn't meet search condition
            if (!searchParameters.getLastname().equals("all")) {
                if (!p.getLastName().toLowerCase().contains(searchParameters.getLastname().toLowerCase())) {
                    // and it is in the ret list
                    if (ret.contains(p)) {
                        // remove it from the ret list
                        ret.remove(p);
                    }
                }
            }

            // if derma's description doesn't meet search condition
            if (!searchParameters.getEmail().equals("all")) {
                if (!p.getEmail().toLowerCase().contains(searchParameters.getEmail().toLowerCase())) {
                    // and it is in the ret list
                    if (ret.contains(p)) {
                        // remove it from the ret list
                        ret.remove(p);
                    }
                }
            }

            // if derma's city doesn't meet search condition
            if (!searchParameters.getCity().equals("all")) {
                if (!p.getCity().toLowerCase().contains(searchParameters.getCity().toLowerCase())) {
                    // and it is in the ret list
                    if (ret.contains(p)) {
                        // remove it from the ret list
                        ret.remove(p);
                    }
                }
            }

            if (searchParameters.getRateTo() != 123456789 || searchParameters.getRateFrom() != -123456789) {
                if (!(p.getRate() <= searchParameters.getRateTo() && p.getRate() >= searchParameters.getRateFrom())) {
                    if (ret.contains(p)) {
                        ret.remove(p);
                    }
                }
            }


        }

        System.out.println("RET : " + ret);

        return ret;
    }

}
