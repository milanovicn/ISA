package com.example.ISABackend.controller;

import com.example.ISABackend.dto.SearchPharmacy;
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
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.core.Context;

import java.util.ArrayList;

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
  
  
    private Pharmacy_Admin authorize(HttpServletRequest request){
        HttpSession session = request.getSession();
        Pharmacy_Admin pa = (Pharmacy_Admin) session.getAttribute("pharmacy_admin");
        return pa;
    }

    @GetMapping(value = "/mydermas/{pharmacyId}")
    public Object getMyDerma(@PathVariable("pharmacyId") Long pharmacyId, @Context HttpServletRequest request) {
        if(authorize(request) == null ) {
            return new ResponseEntity<>( HttpStatus.UNAUTHORIZED);
        }
        return  pharmacyService.getById(pharmacyId).getDermatologist();
    }
    @GetMapping(value = "/mymedicine/{pharmacyId}")
    public Object getMyMedicine(@PathVariable("pharmacyId") Long pharmacyId, @Context HttpServletRequest request) {
        if(authorize(request) == null ) {
            return new ResponseEntity<>( HttpStatus.UNAUTHORIZED);
        }
        return  pharmacyService.getById(pharmacyId).getMedicine();
    }

    @GetMapping(value = "/mypharmas/{pharmacyId}")
    public Object getMyPharma(@PathVariable("pharmacyId") Long pharmacyId, @Context HttpServletRequest request) {
        if(authorize(request) == null ) {
            return new ResponseEntity<>( HttpStatus.UNAUTHORIZED);
        }
        return  pharmacyService.getById(pharmacyId).getPharmacist();
    }


    @PutMapping(value = "/addmedicine/{medicine_id}")
    public ResponseEntity<?> addNewMedicine(@RequestBody Pharmacy updatedUser, @PathVariable("medicine_id") Long medicine_id, @Context HttpServletRequest request) {
        if(authorize(request) == null ) {
            return new ResponseEntity<>( HttpStatus.UNAUTHORIZED);
        }

        Pharmacy u = pharmacyService.addMedicine(updatedUser.getId(), medicine_id);
        if (u == null) {
            return new ResponseEntity<String>("This medicine is already added!", HttpStatus.METHOD_NOT_ALLOWED);
        }
        return new ResponseEntity<Pharmacy>(u, HttpStatus.CREATED);
    }


//    @GetMapping(value = "/myMedicine/{pharmacyId}")
//    public Object getMyMedicine(@PathVariable("pharmacyId") Long pharmacyId, @Context HttpServletRequest request) {
//        if(authorize(request) == null ) {
//            return new ResponseEntity<>( HttpStatus.UNAUTHORIZED);
//        }
//        return  pharmacyService.getById(pharmacyId).getMedicineId();
//    }

}
