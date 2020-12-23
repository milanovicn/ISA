package com.example.ISABackend.controller;

import com.example.ISABackend.model.Pharmacy_Admin;
import com.example.ISABackend.model.User;
import com.example.ISABackend.repository.MedicineRepository;
import com.example.ISABackend.repository.PharmacyAdminRepository;
import com.example.ISABackend.service.MedicineService;
import com.example.ISABackend.service.PharmacyAdminService;
import com.example.ISABackend.service.PharmacyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.core.Context;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;


import java.util.Collection;

@RestController
@RequestMapping("/api/pharmacy-admin")
public class PharmacyAdminController {


    @Autowired
    private PharmacyAdminRepository pharmacyAdminRepository;

    @Autowired
    private PharmacyAdminService pharmacyAdminService;

    @Autowired
    private PharmacyService pharmacyService;

    private Pharmacy_Admin authorize(HttpServletRequest request){
        HttpSession session = request.getSession();
        Pharmacy_Admin pa = (Pharmacy_Admin) session.getAttribute("pharmacy_admin");
        return pa;
    }

    @PutMapping(value = "/firstlogin/{newPassword}")
    public ResponseEntity firstLogIn(@RequestBody Long adminId, @Context HttpServletRequest request, @PathVariable("newPassword") String newPassword) {
        if(authorize(request) == null ) {
            return new ResponseEntity<>( HttpStatus.UNAUTHORIZED);
        }
        Pharmacy_Admin pa1=pharmacyAdminService.promeniLozinku(adminId, newPassword);
        request.getSession().setAttribute("pharmacy_admin", pa1);
        return new ResponseEntity<Pharmacy_Admin>(pa1, HttpStatus.CREATED);
    }

    @GetMapping(value = "/pharmacy/{pharmacyId}")
    public Object getPharmacyById(@PathVariable("pharmacyId") Long pharmacyId, @Context HttpServletRequest request) {
        if (authorize(request) == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        return pharmacyService.getById(pharmacyId);
    }
    @PutMapping(value = "/edit")
    public ResponseEntity update(@RequestBody Pharmacy_Admin updatedUser, @Context HttpServletRequest request) {
        if(authorize(request) == null ) {
            return new ResponseEntity<>( HttpStatus.UNAUTHORIZED);
        }
        Pharmacy_Admin user=pharmacyAdminService.updateAdmin(updatedUser);
        request.getSession().setAttribute("pharmacy_admin", user);
        return new ResponseEntity<Pharmacy_Admin>(user, HttpStatus.CREATED);
    }
    @GetMapping(value = "/pharmacy/getByAdminId/{pharmacyAdminId}")
    public Object getPharmacyByAdminId(@PathVariable("pharmacyAdminId") Long pharmacyAdminId, @Context HttpServletRequest request) {
        if (authorize(request) == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        return pharmacyAdminService.getPharmacyByAdminId(pharmacyAdminId);
    }

    }





