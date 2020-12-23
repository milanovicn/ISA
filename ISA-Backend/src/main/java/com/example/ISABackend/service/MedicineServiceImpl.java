package com.example.ISABackend.service;

import com.example.ISABackend.dto.SearchMedicine;
import com.example.ISABackend.model.Medicine;
import com.example.ISABackend.model.Pharmacy;
import com.example.ISABackend.repository.MedicineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MedicineServiceImpl implements MedicineService {

    @Autowired
    private MedicineRepository medicineRepository;

    @Override
    public Medicine getById(Long id) {
        return  medicineRepository.findById(id).orElseGet(null);
    }

    @Override
    public List<Medicine> getAll() {
        return medicineRepository.findAll();
    }

    @Override
    public ArrayList<Medicine> search(SearchMedicine searchParameters) {
        ArrayList<Medicine> ret = new ArrayList<Medicine>();

        // getting all pharmacies
        for (Medicine m : medicineRepository.findAll()) {
            ret.add(m);
        }

        for (Medicine p : medicineRepository.findAll()) {

            // if medicine contraindications doesn't meet search condition
            if (!searchParameters.getContraindications().equals("all")) {
                if (!p.getContraindications().toLowerCase().contains(searchParameters.getContraindications().toLowerCase())) {
                    // and it is in the ret list
                    if (ret.contains(p)) {
                        // remove it from the ret list
                        ret.remove(p);
                    }
                }
            }

            // if medicine name doesn't meet search condition
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
            if (!searchParameters.getType().equals("all")) {
                if (!p.getType().toString().toLowerCase().contains(searchParameters.getType().toLowerCase())) {
                    // and it is in the ret list
                    if (ret.contains(p)) {
                        // remove it from the ret list
                        ret.remove(p);
                    }
                }
            }

            // if pharmacy city doesn't meet search condition
            if ( searchParameters.getCode() != 0){
                String pCodeParsed =  Integer.toString(p.getCode());
                String searchParametersCodeParsed =  Integer.toString(searchParameters.getCode());
                if (!pCodeParsed.toLowerCase().contains(searchParametersCodeParsed.toLowerCase())) {
                    // and it is in the ret list
                    if (ret.contains(p)) {
                        // remove it from the ret list
                        ret.remove(p);
                    }
                }
            }




        }

        System.out.println("RET : " + ret);

        return ret;
    }


}
