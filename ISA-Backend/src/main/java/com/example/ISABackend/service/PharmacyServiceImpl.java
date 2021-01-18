package com.example.ISABackend.service;

import com.example.ISABackend.dto.SearchPharmacy;
import com.example.ISABackend.model.Medicine;
import com.example.ISABackend.model.Pharmacy;
import com.example.ISABackend.model.User;
import com.example.ISABackend.repository.PharmacyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PharmacyServiceImpl implements PharmacyService {
    @Autowired
    private PharmacyRepository pharmacyRepository;
    @Autowired
    private MedicineService medicineService;

    @Override
    public Pharmacy getById(Long id) {
      return  pharmacyRepository.findById(id).orElseGet(null);
    }

    @Override
    public Collection<Pharmacy> findAll() {
        return pharmacyRepository.findAll();
    }

    @Override
    public ArrayList<Pharmacy> search(SearchPharmacy searchParameters) {

        ArrayList<Pharmacy> ret = new ArrayList<Pharmacy>();

        // getting all pharmacies
        for (Pharmacy p : pharmacyRepository.findAll()) {
            ret.add(p);
        }

        for (Pharmacy p : pharmacyRepository.findAll()) {

            // if pharmacy address doesn't meet search condition
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
            if (!searchParameters.getName().equals("all")) {
                if (!p.getName().toLowerCase().contains(searchParameters.getName().toLowerCase())) {
                    // and it is in the ret list
                    if (ret.contains(p)) {
                        // remove it from the ret list
                        ret.remove(p);
                    }
                }
            }

            // if pharmacy description doesn't meet search condition
            if (!searchParameters.getDescription().equals("all")) {
                if (!p.getDescription().toLowerCase().contains(searchParameters.getDescription().toLowerCase())) {
                    // and it is in the ret list
                    if (ret.contains(p)) {
                        // remove it from the ret list
                        ret.remove(p);
                    }
                }
            }

            // if pharmacy city doesn't meet search condition
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

    @Override
    public ArrayList<Pharmacy> sort(ArrayList<Pharmacy> sortPharmacies, String sortType) {
        if(sortType.equals("RATE")){
            sortPharmacies.sort(Comparator.comparingDouble(Pharmacy :: getRate));
            return sortPharmacies;
        } else if(sortType.equals("CITY")){
            Comparator<Pharmacy> compareByCity = (Pharmacy p1, Pharmacy p2) ->
                    p1.getCity().compareTo( p2.getCity() );
            Collections.sort(sortPharmacies, compareByCity);
            return sortPharmacies;
        } else if(sortType.equals("NAME")){
            Comparator<Pharmacy> compareByName = (Pharmacy p1, Pharmacy p2) ->
                    p1.getName().compareTo( p2.getName() );
            Collections.sort(sortPharmacies, compareByName);
            return sortPharmacies;
        }

        return null;
    }
    public Pharmacy updatePharmacy(Pharmacy updatedPharmacy) {
        Pharmacy fromRepository =  getById(updatedPharmacy.getId());

        fromRepository.setAddress(updatedPharmacy.getAddress());
        fromRepository.setName(updatedPharmacy.getName());
        fromRepository.setDescription(updatedPharmacy.getDescription());


        pharmacyRepository.save(fromRepository);

        return fromRepository;

    }

    @Override
    public Pharmacy addMedicine(Long pharmacy_id, Long medicine_id) {
        Pharmacy u = getById(pharmacy_id);
        Medicine m = medicineService.getById(medicine_id);
        Set<Medicine> myMedicine = u.getMedicine();

        if(!myMedicine.contains(m)) {
            myMedicine.add(m);
            pharmacyRepository.save(u);
            return u;
        }
        else {
            return null;
        }

    }

}
