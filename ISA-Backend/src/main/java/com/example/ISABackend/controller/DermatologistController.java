package com.example.ISABackend.controller;

import com.example.ISABackend.dto.DermatologistAppointmentDTO;
import com.example.ISABackend.dto.SearchPharmacy;
import com.example.ISABackend.model.*;
import com.example.ISABackend.repository.DermatologistRepository;
import com.example.ISABackend.service.DermatologistAppointmentService;
import com.example.ISABackend.service.DermatologistService;
import com.example.ISABackend.service.DermatologistVacationService;
import com.example.ISABackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.core.Context;
import java.util.ArrayList;

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


    @GetMapping(value = "/availableDermatologistAppointments/{dermatologistId}")
    public Object availableDermatologistAppointments(@PathVariable("dermatologistId") Long dermatologistId) {

        return  dermatologistAppointmentService.getApproprietAppoinment(dermatologistId);
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

        DermatologistAppointment dermatologistAppointment = dermatologistAppointmentService.appointmentReserveForUser(appointmentId,patientId );
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

