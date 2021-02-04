package com.example.ISABackend.service;

import com.example.ISABackend.model.DermatologistSchedule;
import com.example.ISABackend.model.PharmacistSchedule;
import com.example.ISABackend.repository.DermatologistScheduleRepository;
import com.example.ISABackend.repository.PharmacistScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PharmacistScheduleServiceImpl implements PharmacistScheduleService{

    @Autowired
    PharmacistScheduleRepository pharmacistScheduleRepository;

    @Override
    public List<PharmacistSchedule> getAll() {
        return pharmacistScheduleRepository.findAll();
    }


    @Override
    public ArrayList<PharmacistSchedule> getFindByPharmacistLong(Long pharmacistId) {
        return pharmacistScheduleRepository.findByPharmacistId(pharmacistId);
    }

    //vraca raspored odredjenog farmaceuta u odredjenoj apoteci
    @Override
    public ArrayList<PharmacistSchedule> getByPharmacistAndPharmacy(Long pharmacistId, Long pharmacyId) {
        List<PharmacistSchedule> all = this.getAll();
        ArrayList<PharmacistSchedule> ret = new ArrayList<PharmacistSchedule>();
        for (PharmacistSchedule ds : all ) {
            if(ds.getPharmacyId()==pharmacyId && ds.getPharmacistId()==pharmacistId){
                ret.add(ds);
            }
        }

        return ret;
    }

    //vraca raspored odredjenog farmaceuta u odredjenoj apoteci na odredjeni dan
    @Override
    public ArrayList<PharmacistSchedule> getByPharmacistAndPharmacyAndDay(Long pharmacistId, Long pharmacyId, String day) {
        List<PharmacistSchedule> all = this.getAll();
        ArrayList<PharmacistSchedule> ret = new ArrayList<PharmacistSchedule>();
        for (PharmacistSchedule ds : all ) {
            if((ds.getPharmacyId()==pharmacyId) && (ds.getPharmacistId()==pharmacistId) && (ds.getWorkDay().toString().toUpperCase().equals(day.toUpperCase()))){
                ret.add(ds);
            }
        }

        return ret;
    }

}
