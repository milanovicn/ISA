package com.example.ISABackend.controller;

import com.example.ISABackend.repository.MedicineRepository;
import com.example.ISABackend.repository.UserRepository;
import com.example.ISABackend.service.MedicineService;
import com.example.ISABackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
