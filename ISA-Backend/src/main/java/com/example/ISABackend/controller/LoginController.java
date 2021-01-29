package com.example.ISABackend.controller;

import com.example.ISABackend.dto.LoginRequest;
import com.example.ISABackend.model.*;
import com.example.ISABackend.repository.UserRepository;
import com.example.ISABackend.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.core.Context;

@RestController
@RequestMapping("/api/pharmacy")
public class LoginController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private PharmacyAdminService pharmacyAdminService;

    @Autowired
    private SystemAdminService systemAdminService;

    @Autowired
    private SupplierService supplierService;

    @Autowired
    private DermatologistService dermatologistService;

    @Autowired
    private PharmacistService pharmacistService;

    @PostMapping(value = "/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest, @Context HttpServletRequest request) {

        User user = userService.getByEmail(loginRequest.getEmail());
        Pharmacist pharmacist = pharmacistService.getByEmail(loginRequest.getEmail());
        Dermatologist dermatologist = dermatologistService.getByEmail(loginRequest.getEmail());
        Pharmacy_Admin pharmacy_admin = pharmacyAdminService.getByEmail(loginRequest.getEmail());
        SystemAdmin systemAdmin = systemAdminService.getByEmail(loginRequest.getEmail());
        Supplier supplier = supplierService.getByEmail(loginRequest.getEmail());

        if (user != null) {
            if (loginRequest.getPassword().equals(user.getPassword())) {
                HttpSession session = request.getSession();
                session.setAttribute("user", user);

                return new ResponseEntity<User>(user, HttpStatus.CREATED);
            }
        } else if (pharmacy_admin != null) {
            if (loginRequest.getPassword().equals(pharmacy_admin.getPassword())) {

                HttpSession session = request.getSession();
                session.setAttribute("pharmacy_admin", pharmacy_admin);

                return new ResponseEntity<Pharmacy_Admin>(pharmacy_admin, HttpStatus.CREATED);
            }
        } else if (systemAdmin != null) {
            if (loginRequest.getPassword().equals(systemAdmin.getPassword())) {

                HttpSession session = request.getSession();
                session.setAttribute("system_admin", systemAdmin);

                return new ResponseEntity<SystemAdmin>(systemAdmin, HttpStatus.CREATED);
            }
        } else if (supplier != null) {
            if (loginRequest.getPassword().equals(supplier.getPassword())) {

                HttpSession session = request.getSession();
                session.setAttribute("supplier", supplier);

                return new ResponseEntity<Supplier>(supplier, HttpStatus.CREATED);
            }
        }

        else if (dermatologist != null) {
            if (loginRequest.getPassword().equals(supplier.getPassword())) {

                HttpSession session = request.getSession();
                session.setAttribute("dermatologist", dermatologist);

                return new ResponseEntity<Dermatologist>(dermatologist, HttpStatus.CREATED);
            }
        }

        else if (pharmacist != null) {
            if (loginRequest.getPassword().equals(pharmacist.getPassword())) {

                HttpSession session = request.getSession();
                session.setAttribute("pharmacist", pharmacist);

                return new ResponseEntity<Pharmacist>(pharmacist, HttpStatus.CREATED);
            }
        }


        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
}

    @PostMapping(value = "/logout")
    public ResponseEntity logOut(@Context HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.invalidate();

        return ResponseEntity.status(200).build();
    }

    @GetMapping(value = "/whoIsLoggedIn")
    public Object whoIsLoggedIn(@Context HttpServletRequest request) {

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        Pharmacy_Admin pharmacy_admin = (Pharmacy_Admin) session.getAttribute("pharmacy_admin");
        Pharmacist pharmacist = (Pharmacist)  session.getAttribute("pharmacist");
        Dermatologist dermatologist = (Dermatologist) session.getAttribute("dermatologist");
        SystemAdmin sa = (SystemAdmin) session.getAttribute("system_admin");
        Supplier supplier = (Supplier) session.getAttribute("supplier");

        if (user != null) {
            return user;
        } else if (pharmacy_admin != null) {
            return pharmacy_admin;
        }else if (sa != null) {
            return sa;
        } else if (supplier != null) {
            return supplier;
        }else if (pharmacist != null) {
            return pharmacist;
        }else if (dermatologist != null) {
            return dermatologist;
        } else {
            return null;
        }
    }

    @PutMapping(value = "/changePassword/{userRole}/{newPassword}")
    public ResponseEntity<?> changePassword(@RequestBody Long id, @Context HttpServletRequest request, @PathVariable("newPassword") String newPassword, @PathVariable("userRole") String userRole) {

        if(userRole.equals("SYSTEM_ADMIN")) {
            HttpSession session = request.getSession();
            SystemAdmin sa = systemAdminService.changePassword(id, newPassword);

            session.setAttribute("system_admin", sa);
            return new ResponseEntity<SystemAdmin>(sa, HttpStatus.CREATED);

        } else  if(userRole.equals("SUPPLIER")) {
            HttpSession session = request.getSession();
            Supplier s = supplierService.changePassword(id, newPassword);

            session.setAttribute("supplier", s);
            return new ResponseEntity<Supplier>(s, HttpStatus.CREATED);

        } else if (userRole.equals("PHARMACIST")) {
            HttpSession session = request.getSession();
            Pharmacist p = pharmacistService.changePassword(id, newPassword);

            session.setAttribute("pharmacist", p);
            return new ResponseEntity<Pharmacist>(p, HttpStatus.CREATED);

        }else if (userRole.equals("DERMATOLOGIST")) {
            HttpSession session = request.getSession();
            Dermatologist d = dermatologistService.changePassword(id, newPassword);

            session.setAttribute("dermatologist", d);
            return new ResponseEntity<Dermatologist>(d, HttpStatus.CREATED);
        }

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

}
