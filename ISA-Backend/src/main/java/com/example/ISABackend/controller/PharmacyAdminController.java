package com.example.ISABackend.controller;

import com.example.ISABackend.repository.MedicineRepository;
import com.example.ISABackend.repository.PharmacyAdminRepository;
import com.example.ISABackend.service.MedicineService;
import com.example.ISABackend.service.PharmacyAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/pharmacy-admin")
public class PharmacyAdminController {

    @Autowired
    private PharmacyAdminRepository pharmacyAdminRepository;

    @Autowired
    private PharmacyAdminService pharmacyAdminService;
}
