package com.example.ISABackend.controller;

import com.example.ISABackend.dto.DermatologistAppointmentDTO;
import com.example.ISABackend.dto.SearchPharmacy;
import com.example.ISABackend.model.*;
import com.example.ISABackend.repository.DermatologistRepository;
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
@RequestMapping("/api/dermatologist")
public class DermatologistController {

    @Autowired
    private DermatologistService dermatologistService;

    @Autowired
    private DermatologistRepository dermatologistRepository;

    @Autowired
    private DermatologistAppointmentService dermatologistAppointmentService;

    @Autowired
    private DermatologistVacationService dermatologistVacationService;

    @Autowired
    private UserService userService;

    @Autowired
    private EmailService emailService;

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

    @PostMapping(value = "/search/{firstname}")
    public Object searchUser(@PathVariable("firstname") String firstname,
                             @RequestBody String lastname, @Context HttpServletRequest request) {

        HttpSession session = request.getSession();
        Dermatologist dermatologist = (Dermatologist) session.getAttribute("dermatologist");

        return dermatologistService.searchUser(firstname, lastname, dermatologist.getId());
    }

    @GetMapping(value = "/patients")
    public Object getMyPatients (@Context HttpServletRequest request) {
        HttpSession session = request.getSession();
        Dermatologist dermatologist = (Dermatologist) session.getAttribute("dermatologist");

        return  dermatologistService.getAllUsers(dermatologist.getId());
    }

    @GetMapping(value = "/reservedAppointments")
    public Object reservedAppointments (@Context HttpServletRequest request) {

        HttpSession session = request.getSession();
        Dermatologist dermatologist = (Dermatologist) session.getAttribute("dermatologist");

        ArrayList<DermatologistAppointmentDTO> appointmentsList =
                dermatologistAppointmentService.getReservedAppointments(dermatologist.getId());

        return appointmentsList;

    }

    @GetMapping(value = "/availableAppointments")
    public Object availableAppointments (@Context HttpServletRequest request) {

        HttpSession session = request.getSession();
        Dermatologist dermatologist = (Dermatologist) session.getAttribute("dermatologist");

        ArrayList<DermatologistAppointmentDTO> appointmentsList =
                dermatologistAppointmentService.getAvailableAppointments(dermatologist.getId());

        return appointmentsList;

    }

    @GetMapping(value = "/appointment/{appointmentId}")
    public Object getAppointemnt (@PathVariable("appointmentId") Long appointmentId) {

        DermatologistAppointmentDTO dermatologistAppointmentDTO = dermatologistAppointmentService.getDTOById(appointmentId);
        return dermatologistAppointmentDTO;
    }

    @GetMapping(value = "/appointmentReserveForUser/{appointmentId}/{patientId}")
    public Object appointmentReserveForUser (@PathVariable("appointmentId") Long appointmentId,
                                             @PathVariable("patientId") Long patientId) {



        User user = userService.getById(patientId);

        Properties props = new Properties();
        props.put("mail.mime.address.strict", "false");
        props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        Session session = Session.getDefaultInstance(props);

        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(user.getEmail());
        mailMessage.setSubject("Complete reservation!");
        mailMessage.setFrom("ISA.tim66@gmail.com");
        mailMessage.setText("To confirm your account, please click here : ");

        DermatologistAppointment dermatologistAppointment = dermatologistAppointmentService.appointmentReserveForUser(appointmentId,patientId );

        if( dermatologistAppointment != null) {
            emailService.sendEmail(mailMessage);
        }


        return dermatologistAppointment;

    }


    @DeleteMapping(value = "/{dermatologistId}")
    public ResponseEntity<Dermatologist> deleteDerma(@PathVariable("dermatologistId") Long dermatologistId,HttpServletRequest request) {
        HttpSession session = request.getSession();
        Pharmacy_Admin d = (Pharmacy_Admin) session.getAttribute("pharmacy_admin");
        dermatologistService.delete(dermatologistId,d.getPharmacy().getId());
        return new ResponseEntity<Dermatologist>(HttpStatus.NO_CONTENT);
    }

    @GetMapping(value = "/myvacations/{dermatologistId}")
    public Object getMyVacations(@PathVariable("dermatologistId") Long dermatologistId, @Context HttpServletRequest request) {
        if (authorize(request) == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        return dermatologistVacationService.getVacations(dermatologistId);
    }


    @PostMapping(value = "/newvacation/{dermatologistId}")
    public Object addVacation(@RequestBody DermatologistVacation newAction, @PathVariable("dermatologistId") Long dermatologistId, @Context HttpServletRequest request) {
        if (authorize(request) == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        DermatologistVacation a = dermatologistVacationService.addVacation(newAction, dermatologistId);
        return new ResponseEntity<DermatologistVacation>(a, HttpStatus.CREATED);
    }


}

