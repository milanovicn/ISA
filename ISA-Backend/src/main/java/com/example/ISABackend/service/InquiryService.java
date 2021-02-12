package com.example.ISABackend.service;

import com.example.ISABackend.model.Inquiry;

public interface InquiryService {
   public Inquiry create(Long pharmacyId, Long reportMedicineId);


}
