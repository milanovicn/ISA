package com.example.ISABackend.repository;


import com.example.ISABackend.model.Inquiry;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;

public interface InquiryRepository extends JpaRepository<Inquiry, Long> {
    ArrayList<Inquiry> findByPharmacyId(Long pharmacyId);

}
