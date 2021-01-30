package com.example.ISABackend.service;


import com.example.ISABackend.model.DermatologistSchedule;
import com.example.ISABackend.repository.DermatologistScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DermatologistScheduleServiceImpl implements DermatologistScheduleService{

    @Autowired
    DermatologistScheduleRepository dermatologistScheduleRepository;

    @Override
    public List<DermatologistSchedule> getAll() {
        return dermatologistScheduleRepository.findAll();
    }

    @Override
    public ArrayList<DermatologistSchedule> getFindByDermatologist(Long dermatologistId) {
        return dermatologistScheduleRepository.findByDermatologistId(dermatologistId);
    }

    //vraca raspored odredjenog dermatologa u odredjenoj apoteci
    @Override
    public ArrayList<DermatologistSchedule> getByDermatologistAndPharmacy(Long dermatologistId, Long pharmacyId) {
        List<DermatologistSchedule> all = this.getAll();
        ArrayList<DermatologistSchedule> ret = new ArrayList<DermatologistSchedule>();
        for (DermatologistSchedule ds : all ) {
            if(ds.getPharmacyId()==pharmacyId && ds.getDermatologistId()==dermatologistId){
                ret.add(ds);
            }
        }

        return ret;
    }

    //vraca raspored odredjenog dermatologa u odredjenoj apoteci na odredjeni dan
    @Override
    public ArrayList<DermatologistSchedule> getByDermatologistAndPharmacyAndDay(Long dermatologistId, Long pharmacyId, String day) {
        List<DermatologistSchedule> all = this.getAll();
        ArrayList<DermatologistSchedule> ret = new ArrayList<DermatologistSchedule>();
        for (DermatologistSchedule ds : all ) {
            if((ds.getPharmacyId()==pharmacyId) && (ds.getDermatologistId()==dermatologistId) && (ds.getWorkDay().toString().toUpperCase().equals(day.toUpperCase()))){
                ret.add(ds);
            }
        }

        return ret;
    }
}
