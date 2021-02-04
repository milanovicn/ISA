package com.example.ISABackend.controller;

import com.example.ISABackend.dto.SearchDermatologist;
import com.example.ISABackend.dto.SearchPharmacist;
import com.example.ISABackend.enums.WorkDays;
import com.example.ISABackend.model.*;
import com.example.ISABackend.repository.PharmacyAdminRepository;
import com.example.ISABackend.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.core.Context;
import java.time.LocalDate;
import java.util.ArrayList;

@RestController
@RequestMapping("/api/pharmacy-admin")
public class PharmacyAdminController {


    @Autowired
    private PharmacyAdminRepository pharmacyAdminRepository;

    @Autowired
    private PharmacyAdminService pharmacyAdminService;

    @Autowired
    private PharmacyService pharmacyService;

    @Autowired
    private PharmacistService pharmacistService;

    @Autowired
    private DermatologistAppointmentService dermatologistAppointmentService;

    @Autowired
    private PharmacistAppointmentService pharmacistAppointmentService;

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

    @PostMapping(value = "/search")
    public Object searchDerma(@RequestBody SearchDermatologist searchParameters, @Context HttpServletRequest request) {
        if (authorize(request) == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        return pharmacyAdminService.searchD(searchParameters);
    }

    @PostMapping(value = "/searchP")
    public Object searchPharma(@RequestBody SearchPharmacist searchParameters, @Context HttpServletRequest request) {
        if (authorize(request) == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        return pharmacyAdminService.searchP(searchParameters);
    }

    @PostMapping(value = "/dermatologistAppointment/{pharmacyId}/{dermatologistId}/{appointmentTime}/{price}")
    public ResponseEntity<?> addDermatologistAppointment(@PathVariable("pharmacyId") Long pharmacyId,
                                                          @PathVariable("dermatologistId") Long dermatologistId,
                                                          @PathVariable("appointmentTime") String appointmentTime,
                                                          @PathVariable("price") Long price,
                                                          @RequestBody LocalDate appointmentDate,
                                                          @Context HttpServletRequest request) {
        if (authorize(request) == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        DermatologistAppointment da = dermatologistAppointmentService.addDermatologistAppointment(pharmacyId, dermatologistId, appointmentTime, price, appointmentDate);
        if (da != null) {
            return new ResponseEntity<DermatologistAppointment>(da, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>( HttpStatus.METHOD_NOT_ALLOWED);
        }
    }
    @PostMapping(value="/pharmacist/{pharmacyId}")
    public ResponseEntity<Pharmacist> registerPharmacist(@RequestBody Pharmacist newPharmacist, @PathVariable("pharmacyId") Long pharmacyId, @Context HttpServletRequest request){
        if(authorize(request) == null ) {
            return new ResponseEntity<>( HttpStatus.UNAUTHORIZED);
        }
        Pharmacist pharmacist = pharmacistService.addNew(newPharmacist, pharmacyId);

        return new ResponseEntity<Pharmacist>(pharmacist, HttpStatus.CREATED);
    }

    @PostMapping(value = "/pharmacistAppointment/{pharmacyId}/{pharmacistId}/{appointmentTime}/{price}")
    public ResponseEntity<?> addPharmacistAppointment(@PathVariable("pharmacyId") Long pharmacyId,
                                                         @PathVariable("pharmacistId") Long pharmacistId,
                                                         @PathVariable("appointmentTime") String appointmentTime,
                                                         @PathVariable("price") Long price,
                                                         @RequestBody LocalDate appointmentDate,
                                                         @Context HttpServletRequest request) {
        if (authorize(request) == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        PharmacistAppointment da = pharmacistAppointmentService.addPharmacistAppointment(pharmacyId, pharmacistId, appointmentTime, price, appointmentDate);
        if (da != null) {
            return new ResponseEntity<PharmacistAppointment>(da, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>( HttpStatus.METHOD_NOT_ALLOWED);
        }
    }
    }





