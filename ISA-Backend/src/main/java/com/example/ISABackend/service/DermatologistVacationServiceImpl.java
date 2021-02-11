package com.example.ISABackend.service;

import com.example.ISABackend.enums.VacationStatus;
import com.example.ISABackend.model.*;
import com.example.ISABackend.repository.DermatologistAppointmentRepository;
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

    @Autowired
    private DermatologistAppointmentRepository dermatologistAppointmentRepository;

    @Autowired
    private DermatologistScheduleService dermatologistScheduleService;

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

       // ArrayList<DermatologistAppointment> myAppointments = dermatologistAppointmentRepository.findByDermatologistId(id);
      // for(DermatologistAppointment da : myAppointments){

      //  }


        DermatologistVacation vacation = new DermatologistVacation();
        vacation.setDermatologistId(id);
        vacation.setDateFrom(newVacation.getDateFrom().plusDays(1));
        vacation.setDateTo(newVacation.getDateTo().plusDays(1));
        vacation.setStatus(VacationStatus.SUBMITED);



        dermatologistVacationRepository.save(vacation);
        return vacation;

    }
    @Override
    public ArrayList<DermatologistVacation> getVacations(Long dermatologistId){
        ArrayList<DermatologistVacation> vacations = dermatologistVacationRepository.findByDermatologistId(dermatologistId);

        return vacations;
    }

    @Override
    public ArrayList<DermatologistVacation> getDermaVacationsForPharmacy(Long pharmacyId){
        ArrayList<DermatologistVacation> ret = new ArrayList<>();
        List<DermatologistVacation> vacations = dermatologistVacationRepository.findAll();
        for(DermatologistVacation prolazim : vacations){
            if(dermatologistScheduleService.workInPharmacy(prolazim.getDermatologistId(),pharmacyId)){
               if(prolazim.getStatus().equals(VacationStatus.SUBMITED))
                ret.add(prolazim);
            }

        }
        return ret;
    }

    @Override
    public DermatologistVacation acceptVacation(Long idOdmora){
        //id dermatologa koji je podneo zahtev za odmor
        DermatologistVacation podneoZahtev = dermatologistVacationRepository.findById(idOdmora).orElseGet(null);
        podneoZahtev.setStatus(VacationStatus.APPROVED);
        dermatologistVacationRepository.save(podneoZahtev);
        return podneoZahtev;
    }

    @Override
    public DermatologistVacation rejectVacation(Long idOdmora){
        //id dermatologa koji je podneo zahtev za odmor
        DermatologistVacation podneoZahtev = dermatologistVacationRepository.findById(idOdmora).orElseGet(null);
        podneoZahtev.setStatus(VacationStatus.REJECTED);
        dermatologistVacationRepository.save(podneoZahtev);
        return podneoZahtev;
    }
}
