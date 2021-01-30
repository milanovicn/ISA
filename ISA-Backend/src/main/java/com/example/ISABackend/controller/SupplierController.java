package com.example.ISABackend.controller;

import com.example.ISABackend.model.Supplier;
import com.example.ISABackend.model.User;
import com.example.ISABackend.repository.SupplierRepository;
import com.example.ISABackend.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.core.Context;

@RestController
@RequestMapping("/api/supplier")
public class SupplierController {

    @Autowired
    SupplierService supplierService;

    @Autowired
    SupplierRepository supplierRepository;



    @PutMapping(value = "/edit")
    public ResponseEntity updateUser(@RequestBody Supplier updatedSupplier, @Context HttpServletRequest request) {
        if(authorize(request) == null ) {
            return new ResponseEntity<>( HttpStatus.UNAUTHORIZED);
        }
        Supplier supplier=supplierService.updateSupplier(updatedSupplier);
        request.getSession().setAttribute("supplier", supplier);
        return new ResponseEntity<Supplier>(supplier, HttpStatus.CREATED);
    }

    private Supplier authorize(HttpServletRequest request){
        HttpSession session = request.getSession();
        Supplier s = (Supplier) session.getAttribute("supplier");
        return s;
    }


}
