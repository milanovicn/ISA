package com.example.ISABackend.service;

import com.example.ISABackend.enums.RatedEntity;
import com.example.ISABackend.model.*;
import com.example.ISABackend.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class RateServiceImpl implements  RateService {

    @Autowired
    RateRepository rateRepository;

    @Autowired
    DermatologistService dermatologistService;

    @Autowired
    PharmacistService pharmacistService;

    @Autowired
    PharmacyService pharmacyService;

    @Autowired
    MedicineService medicineService;

    @Autowired
    MedicineReservationService medicineReservationService;

    @Autowired
    DermatologistAppointmentService dermatologistAppointmentService;

    @Autowired
    PharmacistAppointmentService pharmacistAppointmentService;

    @Autowired
    MedicineReservationRepository medicineReservationRepository;

    @Autowired
    DermatologistAppointmentRepository dermatologistAppointmentRepository;

    @Autowired
    PharmacistAppointmentRepository pharmacistAppointmentRepository;

    @Autowired
    DermatologistRepository dermatologistRepository;

    @Autowired
    PharmacyRepository pharmacyRepository;

    @Autowired
    PharmacistRepository pharmacistRepository;

    @Override
    public Rate newRate(Rate newRate) {
        Rate rate = new Rate();
        rate.setRatedType(newRate.getRatedType());
        rate.setRatedId(newRate.getRatedId());
        rate.setRate(newRate.getRate());
        rate.setPatientId(newRate.getPatientId());
        rate.setReservationId(newRate.getReservationId());

        boolean ret1 = true;
        if(rate.getRatedType().equals(RatedEntity.DERMATOLOGIST)){

            DermatologistAppointment pa = dermatologistAppointmentService.getById(newRate.getReservationId());
            rate.setRatedId(pa.getDermatologistId());

            Dermatologist dermatologist = dermatologistService.getById(rate.getRatedId());
            rate.setRatedSubject("DERMATOLOGIST: " + dermatologist.getFirstName()+" "+dermatologist.getLastName());
            rateRepository.save(rate);
            ret1=updateDermatologistRate(rate.getRatedId(), rate.getReservationId());
        } else if(rate.getRatedType().equals(RatedEntity.PHARMACIST)){
            PharmacistAppointment pa = pharmacistAppointmentService.getById(newRate.getReservationId());
            rate.setRatedId(pa.getPharmacistId());


            Pharmacist pharmacist = pharmacistService.getById(rate.getRatedId());
            rate.setRatedSubject("PHARMACIST: " + pharmacist.getFirstName()+" "+pharmacist.getLastName());
            rateRepository.save(rate);

            ret1=updatePharmacistRate(rate.getRatedId(), rate.getReservationId());
        } else if(rate.getRatedType().equals(RatedEntity.PHARMACY)){
            MedicineReservation pa = medicineReservationService.getById(newRate.getReservationId());
            rate.setRatedId(pa.getPharmacyId());


            Pharmacy pharmacy = pharmacyService.getById(rate.getRatedId());
            rate.setRatedSubject("PHARMACY: " + pharmacy.getName());
            rateRepository.save(rate);

            ret1=updatePharmacyRate(rate.getRatedId(), rate.getReservationId());
        }else if(rate.getRatedType().equals(RatedEntity.MEDICINE)){
            MedicineReservation pa = medicineReservationService.getById(newRate.getReservationId());
            rate.setRatedId(pa.getMedicineId());

            ret1=updateMedicineRate(rate.getReservationId());
            Medicine medicine = medicineService.getById(rate.getRatedId());
            rate.setRatedSubject("MEDICINE: " + medicine.getName());
            rateRepository.save(rate);
       }


         return rate;
    }

    @Override
    public ArrayList<Rate> myRates(Long patientId) {

        return rateRepository.findByPatientId(patientId);
    }

    @Override
    public Rate editRate(Rate updatedRate) {
        Rate rate = rateRepository.findById(updatedRate.getId()).orElseGet(null);

        rate.setRate(updatedRate.getRate());
        rateRepository.save(rate);

        boolean ret1 = true;
        if(rate.getRatedType().equals(RatedEntity.DERMATOLOGIST)){
            ret1=updateDermatologistRate(rate.getRatedId(), rate.getReservationId());
        } else if(rate.getRatedType().equals(RatedEntity.PHARMACIST)){
            ret1=updatePharmacistRate(rate.getRatedId(), rate.getReservationId());
        } else if(rate.getRatedType().equals(RatedEntity.PHARMACY)){
            ret1=updatePharmacyRate(rate.getRatedId(), rate.getReservationId());
        }

        return rate;
    }

    public boolean updatePharmacistRate(Long pharmacistId, Long reservationId){
        boolean ret = false;
        double sum = 0;
        int num = 0;
        Pharmacist pharmacist = pharmacistService.getById(pharmacistId);
        ArrayList<Rate> rates = findRates(pharmacistId, RatedEntity.PHARMACIST);
        PharmacistAppointment pa = pharmacistAppointmentService.getById(reservationId);
        pa.setRated(true);
        pharmacistAppointmentRepository.save(pa);

        for(Rate rate :  rates){
            sum+=rate.getRate();
            num+=1;
        }

        pharmacist.setRate(sum/num);
        pharmacistRepository.save(pharmacist);
        return ret;
    }

    public boolean updateDermatologistRate(Long dermatologistId, Long reservationId){
        boolean ret = false;
        double sum = 0;
        int num = 0;
        Dermatologist dermatologist = dermatologistService.getById(dermatologistId);
        ArrayList<Rate> rates = findRates(dermatologistId, RatedEntity.DERMATOLOGIST);
        DermatologistAppointment da = dermatologistAppointmentService.getById(reservationId);
        da.setRated(true);
        dermatologistAppointmentRepository.save(da);

        for(Rate rate :  rates){
            sum+=rate.getRate();
            num+=1;
        }

        dermatologist.setRate(sum/num);
        dermatologistRepository.save(dermatologist);
        return ret;
    }

    public boolean updatePharmacyRate(Long pharmacyId, Long reservationId){
        boolean ret = false;
        double sum = 0;
        int num = 0;
        Pharmacy pharmacy = pharmacyService.getById(pharmacyId);
        ArrayList<Rate> rates = findRates(pharmacyId, RatedEntity.PHARMACY);

        MedicineReservation mr = medicineReservationService.getById(reservationId);
        mr.setRatedPharmacy(true);
        medicineReservationRepository.save(mr);

        for(Rate rate :  rates){
            sum+=rate.getRate();
            num+=1;
        }

        pharmacy.setRate(sum/num);
        pharmacyRepository.save(pharmacy);
        return ret;
    }

    public boolean updateMedicineRate(Long reservationId){
        MedicineReservation mr = medicineReservationService.getById(reservationId);
        mr.setRatedMedicine(true);
        medicineReservationRepository.save(mr);
        return  false;
    }

    public ArrayList<Rate> findRates(Long entityId, RatedEntity ratedEntity){
        ArrayList<Rate> ret = new ArrayList<>();
        for(Rate rate : rateRepository.findAll()){
            if(rate.getRatedType().equals(ratedEntity) && rate.getRatedId().equals(entityId)){
                ret.add(rate);
            }
        }
        return  ret;
    }

}
