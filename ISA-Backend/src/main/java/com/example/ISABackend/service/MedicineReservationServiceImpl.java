package com.example.ISABackend.service;

import com.example.ISABackend.enums.AppointmentStatus;
import com.example.ISABackend.enums.MedicineReservationStatus;
import com.example.ISABackend.model.*;
import com.example.ISABackend.repository.MedicineReservationRepository;
import com.example.ISABackend.repository.PatientPenaltyRepository;
import com.example.ISABackend.repository.PharmacyStockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class MedicineReservationServiceImpl implements MedicineReservationService {


    @Autowired
    private MedicineReservationRepository medicineReservationRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private MedicineService medicineService;

    @Autowired
    private PharmacyService pharmacyService;

    @Autowired
    private PharmacyStockService pharmacyStockService;

    @Autowired
    private PatientPenaltyRepository patientPenaltyRepository;

    @Autowired
    private PharmacyStockRepository pharmacyStockRepository;


    @Override
    public List<MedicineReservation> getAll() {
        return medicineReservationRepository.findAll();
    }

    @Override
    public MedicineReservation getById(Long id) {
        return medicineReservationRepository.findById(id).orElseGet(null);
    }

    @Override
    public ArrayList<MedicineReservation> getByMedicineId(Long medicineId) {
        return medicineReservationRepository.findByMedicineId(medicineId);
    }

    @Override
    public ArrayList<MedicineReservation> getByPharmacyId(Long pharmacyId) {
        return medicineReservationRepository.findByPharmacyId(pharmacyId);
    }

    @Override
    public ArrayList<MedicineReservation> getByPatientId(Long patientId) {
        return medicineReservationRepository.findByPatientId(patientId);
    }

    @Override
    public MedicineReservation makeReservation(MedicineReservation newReservation) {
        //zabrani rezervaciju ako ima 3 penala
        for(PatientPenalty pp : patientPenaltyRepository.findAll()){
            //ako se pp odnosi na korisnika koji pokusava rezervaciju i ima 3 ili vise penala vrati null
            if(newReservation.getPatientId() == pp.getPatientId() && pp.getPenaltyNumber() > 2) {
                return null;
            }
        }

        MedicineReservation ret = new MedicineReservation();
        ret.setMedicineId(newReservation.getMedicineId());
        ret.setPatientId(newReservation.getPatientId());
        ret.setPharmacyId(newReservation.getPharmacyId());
        ret.setPickUpDate(newReservation.getPickUpDate().plusDays(1));
        ret.setPickedUp(false);
        ret.setStatus(MedicineReservationStatus.RESERVED);

        ret.setPatientEmail(userService.getById(newReservation.getPatientId()).getEmail());
        ret.setPharmacyName(pharmacyService.getById(newReservation.getPharmacyId()).getName());
        ret.setMedicineName(medicineService.getById(newReservation.getMedicineId()).getName());
        ret.setReservationCode( UUID.randomUUID().toString());
        medicineReservationRepository.save(ret);

        Long stockId = pharmacyStockService.updateReservedMedicineStock(ret.getMedicineId(), ret.getPharmacyId());
        if(stockId == null){
            return null;
        }
        return ret;
    }

    @Override
    public MedicineReservation cancelReservation(Long id) {
        MedicineReservation toCancel = getById(id);
        LocalDate now = LocalDate.now();
        // ako je danasnji trenutak nakon 24h pre dana preuzimanja ne dozvoli da je otkaze
        if(now.isAfter(toCancel.getPickUpDate().minusDays(1))){
            return null;
        }

        toCancel.setStatus(MedicineReservationStatus.CANCELED);
        toCancel.setPickedUp(true);
        medicineReservationRepository.save(toCancel);
        Long stockId = pharmacyStockService.updateCanceledMedicineStock(toCancel.getMedicineId(), toCancel.getPharmacyId());
        if(stockId == null){
            return null;
        }
        return toCancel;
    }


    @Override
    public ArrayList<Pharmacy> getPharmaciesWithAvailableMedicine(Long medicineId) {
        ArrayList<Pharmacy> ret = new ArrayList<Pharmacy>();
        //uzmi sve apoteke sa tim lekom
        ArrayList<PharmacyStock> pharmacyStocks = pharmacyStockService.getByMedicineId(medicineId);
        //proiteriraj kroz sva stanja
        for(PharmacyStock iter : pharmacyStocks){
            //ako ima bar jedan nerezervisan lek dodaj apoteku u povratnu listu
            if((iter.getInStock() - iter.getReserved()) > 0){
                Pharmacy pharmacy = pharmacyService.getById(iter.getPharmacyId());
                ret.add(pharmacy);
            }
        }
        return ret;
    }

    @Override
    public ArrayList<Pharmacy> getPharmaciesForPatient(Long patientId) {
        ArrayList<MedicineReservation> byPatient = getByPatientId(patientId);
        ArrayList<Pharmacy> ret = new ArrayList<>();

        for(MedicineReservation mr : byPatient){
            if(mr.getStatus().equals(MedicineReservationStatus.ENDED) ){
                Pharmacy pharmacy = pharmacyService.getById(mr.getPharmacyId());
                if(!ret.contains(pharmacy)){
                    ret.add(pharmacy);
                }
            }
        }


        return ret;
    }

    //vraca rezervaciju sa dobrim kodom ako ne postoji vraca null
    @Override
    public MedicineReservation checkMedicineReservationCode(String reservationCode) {
        LocalDate now = LocalDate.now();
        for(MedicineReservation mr : medicineReservationRepository.findAll()){
            if(mr.getReservationCode().toUpperCase().equals(reservationCode.toUpperCase())){
                if(now.isAfter(mr.getPickUpDate().minusDays(1))){
                    return null;
                }
                return mr;
            }

        }

        return null;
    }

    //zavrsava rezervaciju i updateuje stanje leka u apoteci
    @Override
    public MedicineReservation issueMedicineReservation(Long reservationId) {
       MedicineReservation mr = getById(reservationId);
       mr.setPickedUp(true);
       mr.setStatus(MedicineReservationStatus.ENDED);
       medicineReservationRepository.save(mr);
       ArrayList<PharmacyStock> ps = pharmacyStockService.getByMedicineAndPharmacy(mr.getMedicineId(), mr.getPharmacyId());
       PharmacyStock realOne = ps.get(0);
        realOne.setReserved(realOne.getReserved()-1);
        realOne.setInStock(realOne.getInStock()-1);
        pharmacyStockRepository.save(realOne);
        return mr;
    }

    //trazi nepreuzete rezervacije lekova i dodaje penale korisniku ako nisu preuzeti
    //poziva se svaki dan u 1am

    @Scheduled(cron = "0 0 1 * * ?")
    public void addPenaltyForMedicinesNotPickedUp() {
        boolean patientExisted = false;
        //prolazi kroz sve rezervacije
        for(MedicineReservation medicineReservation :  getAll()){
            //proverava da li postoji ona kojoj je prosao datum preuzimanja a nije podignuta
            if(medicineReservation.getPickUpDate().isAfter(LocalDate.now()) && medicineReservation.getStatus().equals(MedicineReservationStatus.RESERVED) && medicineReservation.isPickedUp()==false){
                //ako postoji dodaje korisniku penal tako sto:
                for(PatientPenalty iter : patientPenaltyRepository.findAll()){
                    //prolazi kroz sve penale
                    if(iter.getPatientId() == medicineReservation.getPatientId()){
                        //ako nadje penale tog pacijenta dodaje mu jedan
                        iter.setPenaltyNumber(iter.getPenaltyNumber()+1);
                        patientPenaltyRepository.save(iter);
                        patientExisted = true;
                    }

                }
                //ako je prosao kroz sve i pacijent nije postojao kreira za njega novi entitet koji pameti penale
                if(!patientExisted){
                    PatientPenalty newPP = new PatientPenalty();
                    newPP.setPenaltyNumber((long) 1);
                    newPP.setPatientId(medicineReservation.getPatientId());
                    patientPenaltyRepository.save(newPP);
                }

            }

        }



    }

    //brise penale svakog prvog u mesecu u podne
    @Scheduled(cron = "0 0 12 1 * ?")
    public void resetPenalty() {
       for (PatientPenalty pp : patientPenaltyRepository.findAll()){
           pp.setPenaltyNumber((long) 0);
           patientPenaltyRepository.save(pp);
       }
    }

}
