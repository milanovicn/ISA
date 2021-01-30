package com.example.ISABackend.controller;


import com.example.ISABackend.model.*;
import com.example.ISABackend.repository.SupplierRepository;
import com.example.ISABackend.repository.SystemAdminRepository;
import com.example.ISABackend.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.core.Context;
import java.util.ArrayList;

@RestController
@RequestMapping("/api/system-admin")
public class SystemAdminController {

    @Autowired
    SystemAdminService systemAdminService;

    @Autowired
    SystemAdminRepository systemAdminRepository;

    @Autowired
    SupplierService supplierService;

    @Autowired
    MedicineService medicineService;

    @Autowired
    PharmacyService pharmacyService;

    @Autowired
    PharmacyAdminService pharmacyAdminService;


    @PostMapping(value="/supplier")
    public ResponseEntity<Supplier> registerSupplier(@RequestBody Supplier newSupplier, @Context HttpServletRequest request){
        if(authorize(request) == null ) {
            return new ResponseEntity<>( HttpStatus.UNAUTHORIZED);
        }
        Supplier supplier = supplierService.addNew(newSupplier);

        return new ResponseEntity<Supplier>(supplier, HttpStatus.CREATED);
    }

    @PostMapping(value="/pharmacy")
    public ResponseEntity<Pharmacy> registerPharmacy(@RequestBody Pharmacy newPharmacy, @Context HttpServletRequest request){
        if(authorize(request) == null ) {
            return new ResponseEntity<>( HttpStatus.UNAUTHORIZED);
        }
        Pharmacy pharmacy = pharmacyService.addNew(newPharmacy);

        return new ResponseEntity<Pharmacy>(pharmacy, HttpStatus.CREATED);
    }

    @PostMapping(value="/pharmacy-admin/{pharmacyId}")
    public ResponseEntity<Pharmacy_Admin> registerPharmacyAdmin(@RequestBody Pharmacy_Admin newPharmacyAdmin, @PathVariable("pharmacyId") Long pharmacyId, @Context HttpServletRequest request){
        if(authorize(request) == null ) {
            return new ResponseEntity<>( HttpStatus.UNAUTHORIZED);
        }
        Pharmacy_Admin pharmacyAdmin = pharmacyAdminService.addNew(newPharmacyAdmin, pharmacyId);

        return new ResponseEntity<Pharmacy_Admin>(pharmacyAdmin, HttpStatus.CREATED);
    }

    @PostMapping(value="/medicine")
    public ResponseEntity<Medicine> registerMedicine(@RequestBody Medicine newMedicine, @Context HttpServletRequest request){
        if(authorize(request) == null ) {
            return new ResponseEntity<>( HttpStatus.UNAUTHORIZED);
        }
        Medicine medicine = medicineService.addNew(newMedicine);

        return new ResponseEntity<Medicine>(medicine, HttpStatus.CREATED);
    }

    @PostMapping(value="/replacements/{medicineId}")
    public ResponseEntity<?> registerMedicine(@RequestBody ArrayList<Long> replacementsId, @PathVariable("medicineId") Long medicineId, @Context HttpServletRequest request){
        if(authorize(request) == null ) {
            return new ResponseEntity<>( HttpStatus.UNAUTHORIZED);
        }
        ArrayList<Long> replacements = medicineService.addReplacements(replacementsId, medicineId);

        return new ResponseEntity<ArrayList<Long>>(replacements, HttpStatus.CREATED);
    }


    private SystemAdmin authorize(HttpServletRequest request){
        HttpSession session = request.getSession();
        SystemAdmin s = (SystemAdmin) session.getAttribute("system_admin");
        return s;
    }

    @PostMapping(value="")
    public ResponseEntity<SystemAdmin> registerSystemAdmin(@RequestBody SystemAdmin newSystemAdmin, @Context HttpServletRequest request){
        if(authorize(request) == null ) {
            return new ResponseEntity<>( HttpStatus.UNAUTHORIZED);
        }
        SystemAdmin systemAdmin = systemAdminService.addNew(newSystemAdmin);

        return new ResponseEntity<SystemAdmin>(systemAdmin, HttpStatus.CREATED);
    }
}
