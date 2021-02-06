package com.example.ISABackend.controller;

import com.example.ISABackend.dto.SearchMedicine;
import com.example.ISABackend.dto.SearchPharmacy;
import com.example.ISABackend.model.*;
import com.example.ISABackend.repository.MedicineRepository;
import com.example.ISABackend.repository.UserRepository;
import com.example.ISABackend.service.MedicineService;
import com.example.ISABackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;

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

    @PostMapping(value = "/sort/{sortType}")
    public Object sortMedicine(@RequestBody ArrayList<Medicine> sortMedicines, @PathVariable("sortType") String sortType) {

        return medicineService.sort(sortMedicines, sortType);
    }

    @PostMapping(value = "/download/{medicine_id}")
    public Object downloadMedicine(@PathVariable("medicine_id") Long medicine_id) {

        return medicineService.download(medicine_id);
    }

}
