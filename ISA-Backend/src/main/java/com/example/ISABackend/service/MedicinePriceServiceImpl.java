package com.example.ISABackend.service;

import com.example.ISABackend.dto.DateInterval;
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
    public MedicinePrice addNewPrice(Long pharmacyId, Long medicineId, DateInterval dateInterval, Long price){
        //ovde treba prvera da li postoji cenovnik za taj lek u tom periodu
        ArrayList<MedicinePrice> medicinePrices = getByMedicineAndPharmacy(medicineId,pharmacyId);
        for(MedicinePrice mp : medicinePrices){
            //proverava da li se preklapaju novi termin vazenja cenovnika sa nekim vec postojecim za taj lek u toj apoteci
            if(mp.getDateFrom().isBefore(dateInterval.getEndDate()) && dateInterval.getStartDate().isBefore(mp.getDateTo())){
                return null;
            }
        }


        MedicinePrice a = new MedicinePrice();
        a.setPharmacyId(pharmacyId);
        a.setMedicineId(medicineId);
        a.setDateFrom(dateInterval.getStartDate().plusDays(1));
        a.setDateTo(dateInterval.getEndDate().plusDays(1));
        a.setPrice(price);
        medicinePriceRepository.save(a);
        return a;

    }


    public ArrayList<MedicinePrice> getMedicinePrice(Long pharmacyId){
        ArrayList<MedicinePrice> akcije = this.getByPharmacy(pharmacyId);

        return akcije;
    }

    @Override
    public ArrayList<MedicinePrice> getByMedicineAndPharmacy(Long medicineId, Long pharmacyId) {
        ArrayList<MedicinePrice> medicinePriceArrayList = getByPharmacy(pharmacyId);
        ArrayList<MedicinePrice> ret = new ArrayList<>();
        for(MedicinePrice medicinePrice: medicinePriceArrayList){
            if(medicineId == medicinePrice.getMedicineId()){
                ret.add(medicinePrice);
            }
        }
        return ret;
    }


}
