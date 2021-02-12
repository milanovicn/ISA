package com.example.ISABackend.service;

import com.example.ISABackend.model.Inquiry;
import com.example.ISABackend.repository.InquiryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class InquiryServiceImpl implements InquiryService{

    @Autowired
    InquiryRepository inquiryRepository;

    @Autowired
    MedicineService medicineService;


    @Override
    public Inquiry create(Long pharmacyId, Long reportMedicineId) {
        Inquiry inquiry = new Inquiry();

        inquiry.setMedicineId(reportMedicineId);
        inquiry.setMedicineName(medicineService.getById(reportMedicineId).getName());
        inquiryRepository.save(inquiry);
        return inquiry;
    }

    @Override
    public ArrayList<Inquiry> getByPharmacy(Long pharmacyId) {
        return inquiryRepository.findByPharmacyId(pharmacyId);
    }
}
