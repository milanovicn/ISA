package com.example.ISABackend.service;

import com.example.ISABackend.model.DermatologistSchedule;
import com.example.ISABackend.model.Medicine;
import com.example.ISABackend.model.PharmacyStock;
import com.example.ISABackend.repository.PharmacyStockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PharmacyStockServiceImpl implements PharmacyStockService {

    @Autowired
    private PharmacyStockRepository pharmacyStockRepository;

    @Autowired
    private MedicineService medicineService;


    @Override
    public PharmacyStock getById(Long id) {
        return pharmacyStockRepository.findById(id).orElseGet(null);
    }

    @Override
    public List<PharmacyStock> getAll() {
        return pharmacyStockRepository.findAll();
    }

    @Override
    public ArrayList<PharmacyStock> getByPharmacyId(Long id) {
        return pharmacyStockRepository.findByPharmacyId(id);
    }

    @Override
    public  ArrayList<PharmacyStock> getByMedicineId(Long id) {
        return pharmacyStockRepository.findByMedicineId(id);
    }

    //dodaje novi lek u apoteku ukoliko on ne postoji
    //ukoliko postoji azurira se njegovo stanje, iako ovo treba da ide samo preko narudzbenice
    //dopustila sam i ovako zbog testiranja, svakako moze da se menja
    @Override
    public Long addNewMedicineInStock(Long pharmacyId, Long medicineId, int quantity) {
        ArrayList<PharmacyStock> pharmacyStocks = this.getByPharmacyId(pharmacyId);

        //prodji kroz sve lekove apoteke
        for(PharmacyStock ps : pharmacyStocks){
            if(ps.getMedicineId() == medicineId){
                //ako je taj lek vec u ponudi, dodaj samo na vec postojecu kolicinu
                ps.setInStock(ps.getInStock()+quantity);
                pharmacyStockRepository.save(ps);
                return medicineId;
            }
        }

        //ako nije postojao ovaj lek u ponudi kreiraj novo stanje za ovaj lek u apoteci
        Medicine medicine = medicineService.getById(medicineId);
        PharmacyStock newPharmacyStock = new PharmacyStock();
        newPharmacyStock.setMedicineId(medicineId);
        newPharmacyStock.setPharmacyId(pharmacyId);
        newPharmacyStock.setInStock(quantity);
        newPharmacyStock.setMedicineName(medicine.getName());
        newPharmacyStock.setReserved(0);
        pharmacyStockRepository.save(newPharmacyStock);
        return medicineId;
    }

    //pronalazi sve lekove u zalihama jedne apoteke
    //vraca samo one koji su na stanju
    @Override
    public ArrayList<Medicine> getMedicineInStock(Long pharmacyId) {
        ArrayList<PharmacyStock> pharmacyStocks = this.getByPharmacyId(pharmacyId);
        ArrayList<Medicine> ret = new ArrayList<Medicine>();

        for(PharmacyStock ps : pharmacyStocks){
            if(ps.getInStock()!=0) {
                ret.add(medicineService.getById(ps.getMedicineId()));
            }
        }

        return ret;
    }

    //pronalazi sva stanja u zalihama jedne apoteke
    //vraca sve zalihe cak i tamo gde ih nema na stanju
    //ovo nije vazno moze da se napravi i da vraca samo one koji su na stanju
    //samo se otkomentarise kod dole, iskreno ne znam sta je bolje
    @Override
    public ArrayList<PharmacyStock> getPharmacyStock(Long pharmacyId) {
        //ArrayList<PharmacyStock> pharmacyStocks = this.getByPharmacyId(pharmacyId);
        //ArrayList<PharmacyStock> ret = new ArrayList<PharmacyStock>();

        //for(PharmacyStock ps : pharmacyStocks){
            //ako ga ima u zalihama dodaj ga u listu
            //if(ps.getInStock()!=0) {
            //    ret.add(ps);
            //}
        //}

       // return ret;
        return this.getByPharmacyId(pharmacyId);
    }

    @Override
    public ArrayList<PharmacyStock> getByMedicineAndPharmacy(Long medicineId, Long pharmacyId) {
        List<PharmacyStock> all = this.getAll();
        ArrayList<PharmacyStock> ret = new ArrayList<PharmacyStock>();
        for (PharmacyStock ds : all ) {
            if(ds.getPharmacyId()==pharmacyId && ds.getMedicineId()==medicineId){
                ret.add(ds);
            }
        }

        return ret;
    }

}
