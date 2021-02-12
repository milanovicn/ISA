package com.example.ISABackend.service;

import com.example.ISABackend.model.Inquiry;

import java.util.ArrayList;

public interface InquiryService {
   public Inquiry create(Long pharmacyId, Long reportMedicineId);
   public ArrayList<Inquiry> getByPharmacy(Long pharmacyId);

}
