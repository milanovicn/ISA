package com.example.ISABackend.controller;

import com.example.ISABackend.model.Pharmacy;
import com.example.ISABackend.model.Pharmacy_Admin;
import com.example.ISABackend.model.User;
import com.example.ISABackend.repository.PharmacyRepository;
import com.example.ISABackend.repository.UserRepository;
import com.example.ISABackend.service.PharmacyService;
import com.example.ISABackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.core.Context;
@RestController
@RequestMapping("/api/pharmacy")

public class PharmacyController {

    @Autowired
    private PharmacyRepository pharmacyRepository;

    @Autowired
    private PharmacyService pharmacyService;


    @PutMapping(value = "/editPharmacy")
    public ResponseEntity updateUser(@RequestBody Pharmacy updatedPharmacy, @Context HttpServletRequest request) {
        if(authorize(request) == null ) {
            return new ResponseEntity<>( HttpStatus.UNAUTHORIZED);
        }
        Pharmacy pharmacy=pharmacyService.updatePharmacy(updatedPharmacy);
        //request.getSession().setAttribute("pharmacy", pharmacy);
        return new ResponseEntity<Pharmacy>(pharmacy, HttpStatus.CREATED);
    }

    private Pharmacy_Admin authorize(HttpServletRequest request){
        HttpSession session = request.getSession();
        Pharmacy_Admin pa = (Pharmacy_Admin) session.getAttribute("pharmacy_admin");
        return pa;
    }
}
