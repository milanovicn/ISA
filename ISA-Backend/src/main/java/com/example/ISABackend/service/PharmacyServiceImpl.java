package com.example.ISABackend.service;

import com.example.ISABackend.dto.SearchPharmacy;
import com.example.ISABackend.enums.WorkDays;
import com.example.ISABackend.model.*;
import com.example.ISABackend.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class PharmacyServiceImpl implements PharmacyService {
    @Autowired
    private PharmacyRepository pharmacyRepository;

    @Autowired
    private MedicineService medicineService;

    @Autowired
    private PharmacyService pharmacyService;

    @Autowired
    private PharmacistService pharmacistService;

    @Autowired
    private PharmacyStockRepository pharmacyStockRepository;

    @Autowired
    private PharmacyStockService pharmacyStockService;

    @Autowired
    private DermatologistScheduleRepository dermatologistScheduleRepository;

    @Autowired
    private PharmacistScheduleRepository pharmacistScheduleRepository;

    @Autowired
    private DermatologistService dermatologistService;

    @Autowired
    private PharmacistRepository pharmacistRepository;

    @Override
    public Pharmacy getById(Long id) {
      return  pharmacyRepository.findById(id).orElseGet(null);
    }

    @Override
    public Collection<Pharmacy> findAll() {
        return pharmacyRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public ArrayList<Pharmacy> search(SearchPharmacy searchParameters) {

        ArrayList<Pharmacy> ret = new ArrayList<Pharmacy>();

        // getting all pharmacies
        for (Pharmacy p : pharmacyRepository.findAll()) {
            ret.add(p);
        }

        for (Pharmacy p : pharmacyRepository.findAll()) {

            // if pharmacy address doesn't meet search condition
            if (!searchParameters.getAddress().equals("all")) {
                if (!p.getAddress().toLowerCase().contains(searchParameters.getAddress().toLowerCase())) {
                    // and it is in the ret list
                    if (ret.contains(p)) {
                        // remove it from the ret list
                        ret.remove(p);
                    }
                }
            }

            // if pharmacy name doesn't meet search condition
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
            if (!searchParameters.getDescription().equals("all")) {
                if (!p.getDescription().toLowerCase().contains(searchParameters.getDescription().toLowerCase())) {
                    // and it is in the ret list
                    if (ret.contains(p)) {
                        // remove it from the ret list
                        ret.remove(p);
                    }
                }
            }

            // if pharmacy city doesn't meet search condition
            if (!searchParameters.getCity().equals("all")) {
                if (!p.getCity().toLowerCase().contains(searchParameters.getCity().toLowerCase())) {
                    // and it is in the ret list
                    if (ret.contains(p)) {
                        // remove it from the ret list
                        ret.remove(p);
                    }
                }
            }

            if (searchParameters.getRateTo() != 123456789 || searchParameters.getRateFrom() != -123456789) {
                if (!(p.getRate() <= searchParameters.getRateTo() && p.getRate() >= searchParameters.getRateFrom())) {
                    if (ret.contains(p)) {
                        ret.remove(p);
                    }
                }
            }


        }

        System.out.println("RET : " + ret);

        return ret;
    }

    @Override
    public ArrayList<Pharmacy> sort(ArrayList<Pharmacy> sortPharmacies, String sortType) {
        if(sortType.equals("RATE")){
            sortPharmacies.sort(Comparator.comparingDouble(Pharmacy :: getRate));
            return sortPharmacies;
        } else if(sortType.equals("CITY")){
            Comparator<Pharmacy> compareByCity = (Pharmacy p1, Pharmacy p2) ->
                    p1.getCity().compareTo( p2.getCity() );
            Collections.sort(sortPharmacies, compareByCity);
            return sortPharmacies;
        } else if(sortType.equals("NAME")){
            Comparator<Pharmacy> compareByName = (Pharmacy p1, Pharmacy p2) ->
                    p1.getName().compareTo( p2.getName() );
            Collections.sort(sortPharmacies, compareByName);
            return sortPharmacies;
        }

        return null;
    }

    @Override
    public Pharmacy updatePharmacy(Pharmacy updatedPharmacy) {
        Pharmacy fromRepository =  getById(updatedPharmacy.getId());

        fromRepository.setAddress(updatedPharmacy.getAddress());
        fromRepository.setName(updatedPharmacy.getName());
        fromRepository.setDescription(updatedPharmacy.getDescription());


        pharmacyRepository.save(fromRepository);

        return fromRepository;

    }

    @Override
    public Pharmacy addMedicine(Long pharmacy_id, Long medicine_id) {
        Pharmacy u = getById(pharmacy_id);
        Medicine m = medicineService.getById(medicine_id);
        ArrayList<Medicine> myMedicine = new ArrayList<Medicine>();

        if(!myMedicine.contains(m)) {
            myMedicine.add(m);
            pharmacyRepository.save(u);
            return u;
        } else {
            return null;
        }

    }


    @Override
    public Long deleteMedicine(Long pharmacy_id, Long medicine_id){
        Pharmacy u = getById(pharmacy_id);
        Medicine m = medicineService.getById(medicine_id);
        //uzela sam sve lekove i ako je neki rezerv ne brisem ga
        ArrayList<PharmacyStock> ps = pharmacyStockService.getByMedicineId(medicine_id);
        for(PharmacyStock prolazim : ps)
        {
            if(prolazim.getReserved() != 0){
                return null;
            }
        }
        ArrayList<PharmacyStock> listaLekova = pharmacyStockService.getByMedicineAndPharmacy(medicine_id,pharmacy_id);
        for(PharmacyStock x : listaLekova){
            x.setPharmacyId((long) 0);
            pharmacyStockRepository.save(x);
        }
        m.setId((long) 0);
        //pharmacyRepository.save(u);
        return medicine_id;
    }


    @Override
    public Pharmacy addNew(Pharmacy newPharmacy) {

        Pharmacy s = new Pharmacy();

        s.setName(newPharmacy.getName());
        s.setAddress(newPharmacy.getAddress());
        s.setCity(newPharmacy.getCity());
        s.setDescription(newPharmacy.getDescription());
        s.setRate(0);

        pharmacyRepository.save(s);
        return s;

    }

    //vraca sve dermatologe koji rade u apoteci sa id = pharmacyId
    @Override
    public ArrayList<Dermatologist> getDermatologists(Long pharmacyId) {
        //lista koja se puni dermatolozima koji rade u toj apoteci
        ArrayList<Dermatologist> ret = new ArrayList<Dermatologist>();

        List<DermatologistSchedule> allSchedules = dermatologistScheduleRepository.findAll();
        for(DermatologistSchedule ds : allSchedules){
            //prodji kroz sve rasporede i pronadji one vezane za tu apoteku
            if(ds.getPharmacyId() == pharmacyId){
                //preuzmi dermatologa iz tog rasporeda
                Dermatologist d = dermatologistService.getById(ds.getDermatologistId());
                //ako se vec ne nalazi u ret listi dodaj ga u nju
                if(!ret.contains(d)){
                    ret.add(d);
                }

            }
        }
        return ret;
    }

    @Override
    public ArrayList<Dermatologist> getAvailableDermatologists(Long pharmacyId) {
        ArrayList<Dermatologist> ret = new ArrayList<Dermatologist>();
        ArrayList<Dermatologist> existingDermatologists = this.getDermatologists(pharmacyId);

        for(Dermatologist curr : dermatologistService.getAll()){
            //ako lista dermatologa koji tu vec rade ne sadrzi ovog trenutnog
            if(!existingDermatologists.contains(curr)){
                //dodaj ga u ret listu
                ret.add(curr);
            }
        }



        return ret;
    }

    @Override
    public Long scheduleDermatologist(Long pharmacyId, Long dermatologistId, ArrayList<WorkDays> workDays) {
        ArrayList<DermatologistSchedule> schedules = dermatologistScheduleRepository.findByDermatologistId(dermatologistId);

        //provera da li je zaposlen neki od ovih dana
        for(DermatologistSchedule ds : schedules){
            for(WorkDays day : workDays){
                if(ds.getWorkDay().toString().equals(day.toString())){
                    return null;
                }
            }
        }

        //ako prodje uspesno proveru kreiraj radno vreme za sve dane iz liste
        for(WorkDays newWorkDay : workDays){
            DermatologistSchedule newSchedule = new DermatologistSchedule();
            newSchedule.setPharmacyId(pharmacyId);
            newSchedule.setDermatologistId(dermatologistId);
            newSchedule.setWorkDay(newWorkDay);
            dermatologistScheduleRepository.save(newSchedule);
        }


        return dermatologistId;
    }

    public Long schedulePharmacist(Long pharmacyId, Long pharmacistId, ArrayList<WorkDays> workDays){

        ArrayList<PharmacistSchedule> schedules = pharmacistScheduleRepository.findByPharmacistId(pharmacistId);

        //provera da li je zaposlen neki od ovih dana
        for(PharmacistSchedule ds : schedules){
            for(WorkDays day : workDays){
                if(ds.getWorkDay().toString().equals(day.toString())){
                    return null;
                }
            }
        }

        //ako prodje uspesno proveru kreiraj radno vreme za sve dane iz liste
        for(WorkDays newWorkDay : workDays){
            PharmacistSchedule newSchedule = new PharmacistSchedule();
            newSchedule.setPharmacyId(pharmacyId);
            newSchedule.setPharmacistId(pharmacistId);
            newSchedule.setWorkDay(newWorkDay);
            pharmacistScheduleRepository.save(newSchedule);
        }


        return pharmacistId;
    }
    //vraca sve farmaceute koji rade u apoteci sa id = pharmacyId
    @Override
    public ArrayList<Pharmacist> getPharmacists(Long pharmacyId) {
        //lista koja se puni farmaceutima koji rade u toj apoteci
        ArrayList<Pharmacist> ret = new ArrayList<Pharmacist>();

        List<Pharmacist> svifarm = pharmacistRepository.findAll();
        for(Pharmacist saovimprolazim : svifarm){
            //prodji kroz sve id farmac i pronadji one vezane za id izabrane apoteke
            if(saovimprolazim.getPharmacyId() == pharmacyId){
                //preuzmi farmaceuta
                Pharmacist d = pharmacistService.getById(saovimprolazim.getId());
                //ako se vec ne nalazi u ret listi dodaj ga u nju
                if(!ret.contains(d)){
                    ret.add(d);
                }

            }
        }
        return ret;
    }

    @Override
    public ArrayList<Pharmacist> getAvailablePharmacists(Long pharmacyId) {
        ArrayList<Pharmacist> ret = new ArrayList<Pharmacist>();
        ArrayList<Pharmacist> existingPharmacists = this.getPharmacists(pharmacyId);

        for(Pharmacist curr : pharmacistService.getAll()){
            //ako lista dermatologa koji tu vec rade ne sadrzi ovog trenutnog
            if(!existingPharmacists.contains(curr)){
                //dodaj ga u ret listu
                ret.add(curr);
            }
        }



        return ret;
    }



    }


