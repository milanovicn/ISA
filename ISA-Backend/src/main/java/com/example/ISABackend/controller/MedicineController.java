package com.example.ISABackend.controller;

import com.example.ISABackend.dto.SearchMedicine;
import com.example.ISABackend.dto.SearchPharmacy;
import com.example.ISABackend.repository.MedicineRepository;
import com.example.ISABackend.repository.UserRepository;
import com.example.ISABackend.service.MedicineService;
import com.example.ISABackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/medicine")
public class MedicineController {

    @Autowired
    private MedicineRepository medicineRepository;

    @Autowired
    private MedicineService medicineService;

    @GetMapping(value = "")
    public Object getAll() {

        return medicineService.getAll();
    }

    @PostMapping(value = "/search")
    public Object searchPharmacy(@RequestBody SearchMedicine searchParameters) {

        return medicineService.search(searchParameters);
    }
}
