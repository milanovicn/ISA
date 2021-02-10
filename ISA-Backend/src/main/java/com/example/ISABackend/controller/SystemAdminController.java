package com.example.ISABackend.controller;


import com.example.ISABackend.model.*;
import com.example.ISABackend.repository.SupplierRepository;
import com.example.ISABackend.repository.SystemAdminRepository;
import com.example.ISABackend.service.*;
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
@RequestMapping("/api/system-admin")
public class SystemAdminController {

    @Autowired
    SystemAdminService systemAdminService;

    @Autowired
    SupplierService supplierService;

    @Autowired
    MedicineService medicineService;

    @Autowired
    PharmacyService pharmacyService;

    @Autowired
    PharmacyAdminService pharmacyAdminService;

    @Autowired
    private EmailService emailService;

    @Autowired
    private ComplaintService complaintService;



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

    @GetMapping(value="complaints")
    public ResponseEntity<?> getUnanswered( @Context HttpServletRequest request){
        if(authorize(request) == null ) {
            return new ResponseEntity<>( HttpStatus.UNAUTHORIZED);
        }
        ArrayList<Complaint> complaints = complaintService.getUnanswered();

        return new ResponseEntity< ArrayList<Complaint>>(complaints, HttpStatus.ACCEPTED);
    }

    @PostMapping(value="answerComplaint/{complaintId}")
    public ResponseEntity<?> answerComplaint( @PathVariable("complaintId") Long complaintId,
                                              @RequestBody String answer,
                                              @Context HttpServletRequest request){
        if(authorize(request) == null ) {
            return new ResponseEntity<>( HttpStatus.UNAUTHORIZED);
        }
        Complaint complaint = complaintService.answer(answer,complaintId);

        if(complaint==null){
            return new ResponseEntity<Object>(null, HttpStatus.ACCEPTED);
        }

        Properties props = new Properties();
        props.put("mail.mime.address.strict", "false");
        props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        Session session = Session.getDefaultInstance(props);

        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(complaint.getPatientEmail());
        mailMessage.setSubject("Complaint Answer");
        mailMessage.setFrom("ISA.tim66@gmail.com");
        mailMessage.setText("Your complaint for: " + complaint.getComplaintSubject()
                + "\nDescription: "+complaint.getComplainText() + ". \nAdministrator answer: " + complaint.getComplaintAnswer());

        emailService.sendEmail(mailMessage);
        return new ResponseEntity<Complaint>(complaint, HttpStatus.ACCEPTED);
    }


}
