package com.example.ISABackend.service;

import com.example.ISABackend.model.Actions;
import com.example.ISABackend.model.MedicinePrice;
import com.example.ISABackend.repository.ActionsRepository;
import com.example.ISABackend.repository.MedicinePriceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class MedicinePriceServiceImpl implements  MedicinePriceService {

    @Autowired
    private MedicinePriceRepository medicinePriceRepository;

    @Override
    public MedicinePrice getById(Long id){

        return  medicinePriceRepository.findById(id).orElseGet(null);
    }

    @Override
    public List<MedicinePrice> getAll(){

        return medicinePriceRepository.findAll();
    }

    @Override
    public ArrayList<MedicinePrice> getByPharmacy(Long pharmacyId){
        return medicinePriceRepository.findByPharmacyId(pharmacyId);
    }

    @Override
    public MedicinePrice addNewPrice(Long pharmacyId, Long medicineId, LocalDate dateFrom,LocalDate dateTo, Long price){
        dateFrom=dateFrom.plusDays(1);
        dateTo=dateTo.plusDays(1);
        MedicinePrice a = new MedicinePrice();
        a.setPharmacyId(pharmacyId);
        a.setMedicineId(medicineId);
        a.setDateFrom(dateFrom);
        a.setDateTo(dateTo);
        a.setPrice(price);
        medicinePriceRepository.save(a);
        return a;

    }


    public ArrayList<MedicinePrice> getMedicinePrice(Long pharmacyId){
        ArrayList<MedicinePrice> akcije = this.getByPharmacy(pharmacyId);

        return akcije;
    }


}
