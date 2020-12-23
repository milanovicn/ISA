package com.example.ISABackend.controller;

import com.example.ISABackend.dto.SearchPharmacy;
import com.example.ISABackend.model.Pharmacy;
import com.example.ISABackend.service.MedicineService;
import com.example.ISABackend.service.PharmacyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/pharmacy")
public class PharmacyController {

    @Autowired
    private PharmacyService pharmacyService;

    @GetMapping(value = "")
    public Object getAll() {

        return pharmacyService.findAll();
    }

    @PostMapping(value = "/search")
    public Object searchPharmacy(@RequestBody SearchPharmacy searchParameters) {

        return pharmacyService.search(searchParameters);
    }

    @PostMapping(value = "/sort/{sortType}")
    public Object sortPharmacy(@RequestBody ArrayList<Pharmacy> sortPharmacies, @PathVariable("sortType") String sortType) {

        return pharmacyService.sort(sortPharmacies, sortType);
    }

}
