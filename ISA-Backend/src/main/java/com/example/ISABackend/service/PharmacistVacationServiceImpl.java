package com.example.ISABackend.service;

import com.example.ISABackend.enums.VacationStatus;
import com.example.ISABackend.model.Actions;
import com.example.ISABackend.model.PharmacistVacation;
import com.example.ISABackend.repository.PharmacistVacationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PharmacistVacationServiceImpl implements PharmacistVacationService {

    @Autowired
    private PharmacyService pharmacyService;

    @Autowired
    private PharmacistVacationService pharmacistVacationService;

    @Autowired
    private PharmacistVacationRepository pharmacistVacationRepository;
    @Override
    public PharmacistVacation getById(Long id){
        return  pharmacistVacationRepository.findById(id).orElseGet(null);
    }

    @Override
    public List<PharmacistVacation> getAll(){
        return pharmacistVacationRepository.findAll();
    }


    @Override
    public PharmacistVacation addVacation(PharmacistVacation newVacation, Long id){

        PharmacistVacation vacation = new PharmacistVacation();
        vacation.setPharmacistId(id);
        vacation.setDateFrom(newVacation.getDateFrom().plusDays(1));
        vacation.setDateTo(newVacation.getDateTo().plusDays(1));
        vacation.setStatus(VacationStatus.SUBMITED);

        pharmacistVacationRepository.save(vacation);
        return vacation;

    }
    public ArrayList<PharmacistVacation> getVacations(Long pharmacistId){
        ArrayList<PharmacistVacation> vacations = pharmacistVacationRepository.findByPharmacistId(pharmacistId);

        return vacations;
    }
}
