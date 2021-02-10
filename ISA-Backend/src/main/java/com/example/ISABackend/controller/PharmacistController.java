package com.example.ISABackend.controller;

import com.example.ISABackend.model.*;
import com.example.ISABackend.repository.PharmacistRepository;
import com.example.ISABackend.service.PharmacistService;
import com.example.ISABackend.service.PharmacistVacationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.web.bind.annotation.*;

import javax.mail.Session;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.core.Context;
import java.util.ArrayList;
import java.util.Properties;

@RestController
@RequestMapping("/api/pharmacist")
public class PharmacistController {

    @Autowired
    private PharmacistService pharmacistService;

    @Autowired
    private PharmacistRepository pharmacistRepository;

    @Autowired
    PharmacistVacationService pharmacistVacationService;

    @PutMapping(value = "/edit")
    public ResponseEntity updatePharmacist(@RequestBody Pharmacist updatePharmacist, @Context HttpServletRequest request) {
        if(authorize(request) == null ) {
            return new ResponseEntity<>( HttpStatus.UNAUTHORIZED);
        }
        Pharmacist pharmacist=pharmacistService.updatePharmacist(updatePharmacist);
        request.getSession().setAttribute("pharmacist", pharmacist);
        return new ResponseEntity<Pharmacist>(pharmacist, HttpStatus.CREATED);
    }

    private Pharmacist authorize(HttpServletRequest request){
        HttpSession session = request.getSession();
        Pharmacist p = (Pharmacist) session.getAttribute("pharmacist");
        return p;
    }

    @DeleteMapping(value = "/{pharmacistId}")
    public ResponseEntity<Pharmacist> deletePharma(@PathVariable("pharmacistId") Long pharmacistId) {
        pharmacistService.delete(pharmacistId);
        return new ResponseEntity<Pharmacist>(HttpStatus.NO_CONTENT);
    }

    @GetMapping(value = "/myvacations/{pharmacistId}")
    public Object getMyVacations(@PathVariable("pharmacistId") Long pharmacistId, @Context HttpServletRequest request) {
        if (authorize(request) == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        return pharmacistVacationService.getVacations(pharmacistId);
    }


    @PostMapping(value = "/newvacation/{pharmacistId}")
    public Object addVacation(@RequestBody PharmacistVacation newAction, @PathVariable("pharmacistId") Long pharmacistId, @Context HttpServletRequest request) {
        if (authorize(request) == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        PharmacistVacation a = pharmacistVacationService.addVacation(newAction, pharmacistId);


        return new ResponseEntity<PharmacistVacation>(a, HttpStatus.CREATED);
    }

}
