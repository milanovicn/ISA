package com.example.ISABackend.controller;

import com.example.ISABackend.model.Dermatologist;
import com.example.ISABackend.model.Pharmacist;
import com.example.ISABackend.repository.DermatologistRepository;
import com.example.ISABackend.service.DermatologistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.core.Context;

@RestController
@RequestMapping("/api/dermatologist")
public class DermatologistController {

    @Autowired
    private DermatologistService dermatologistService;

    @Autowired
    private DermatologistRepository dermatologistRepository;

    @PutMapping(value = "/edit")
    public ResponseEntity updateDermatologist(@RequestBody Dermatologist updateDermatologist, @Context HttpServletRequest request) {
        if(authorize(request) == null ) {
            return new ResponseEntity<>( HttpStatus.UNAUTHORIZED);
        }
        Dermatologist dermatologist=dermatologistService.updateDermatologist(updateDermatologist);
        request.getSession().setAttribute("dermatologist", dermatologist);
        return new ResponseEntity<Dermatologist>(dermatologist, HttpStatus.CREATED);
    }

    private Dermatologist authorize(HttpServletRequest request){
        HttpSession session = request.getSession();
        Dermatologist d = (Dermatologist) session.getAttribute("dermatologist");
        return d;
    }
    @DeleteMapping(value = "/{dermatologistId}")
    public ResponseEntity<Dermatologist> deleteDerma(@PathVariable("dermatologistId") Long dermatologistId) {
        dermatologistService.delete(dermatologistId);
        return new ResponseEntity<Dermatologist>(HttpStatus.NO_CONTENT);
    }
}

