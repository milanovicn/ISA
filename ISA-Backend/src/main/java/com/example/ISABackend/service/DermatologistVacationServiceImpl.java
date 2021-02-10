package com.example.ISABackend.service;

import com.example.ISABackend.enums.VacationStatus;
import com.example.ISABackend.model.Dermatologist;
import com.example.ISABackend.model.DermatologistVacation;
import com.example.ISABackend.model.PharmacistVacation;
import com.example.ISABackend.repository.DermatologistVacationRepository;
import com.example.ISABackend.repository.PharmacistVacationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DermatologistVacationServiceImpl implements DermatologistVacationService{
    @Autowired
    private DermatologistVacationService dermatologistVacationService;

    @Autowired
    private DermatologistVacationRepository dermatologistVacationRepository;
    @Override
    public DermatologistVacation getById(Long id){
        return  dermatologistVacationRepository.findById(id).orElseGet(null);
    }

    @Override
    public List<DermatologistVacation> getAll(){
        return dermatologistVacationRepository.findAll();
    }


    @Override
    public DermatologistVacation addVacation(DermatologistVacation newVacation, Long id){

        DermatologistVacation vacation = new DermatologistVacation();
        vacation.setDermatologistId(id);
        vacation.setDateFrom(newVacation.getDateFrom().plusDays(1));
        vacation.setDateTo(newVacation.getDateTo().plusDays(1));
        vacation.setStatus(VacationStatus.SUBMITED);

        dermatologistVacationRepository.save(vacation);
        return vacation;

    }
    public ArrayList<DermatologistVacation> getVacations(Long dermatologistId){
        ArrayList<DermatologistVacation> vacations = dermatologistVacationRepository.findByDermatologistId(dermatologistId);

        return vacations;
    }
}
