package com.example.ISABackend.controller;

import com.example.ISABackend.dto.DermatologistAppointmentDTO;
import com.example.ISABackend.dto.PharmacistAppointmentDTO;
import com.example.ISABackend.model.*;
import com.example.ISABackend.repository.ConfirmationTokenRepository;
import com.example.ISABackend.repository.PharmacistRepository;
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
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Properties;

@RestController
@RequestMapping("/api/pharmacist")
public class PharmacistController {
    @Autowired
    private EmailService emailService;

    @Autowired
    private PharmacistService pharmacistService;

    @Autowired
    private PharmacistRepository pharmacistRepository;

    @Autowired
    PharmacistVacationService pharmacistVacationService;

    @Autowired
    MedicineReservationService medicineReservationService;

    @Autowired
    PharmacistAppointmentService pharmacistAppointmentService;

    @Autowired
    UserService userService;


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


    @GetMapping(value = "/checkMedicineReservationCode/{reservationCode}")
    public Object checkMedicineReservationCode( @PathVariable("reservationCode") String reservationCode, @Context HttpServletRequest request) {
        if (authorize(request) == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        MedicineReservation a = medicineReservationService.checkMedicineReservationCode(reservationCode);

        if (a == null) {
            return new ResponseEntity<Object>(null, HttpStatus.ACCEPTED);
        } else  {
            return new ResponseEntity<MedicineReservation>(a, HttpStatus.CREATED);
        }

    }

    @GetMapping(value = "/issueMedicineReservation/{reservationId}")
    public Object checkMedicineReservationCode( @PathVariable("reservationId") Long reservationId, @Context HttpServletRequest request) {
        if (authorize(request) == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        MedicineReservation a = medicineReservationService.issueMedicineReservation(reservationId);

        if (a == null) {
            return new ResponseEntity<Object>(null, HttpStatus.ACCEPTED);
        } else  {
            HttpSession sessionUsr = request.getSession();
            User user = (User) sessionUsr.getAttribute("user");
            Properties props = new Properties();
            props.put("mail.mime.address.strict", "false");
            props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
            Session session = Session.getDefaultInstance(props);

            LocalDateTime now = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String formatDateTime = now.format(formatter);

            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setTo(user.getEmail());
            mailMessage.setSubject("Medicine issued!");
            mailMessage.setFrom("ISA.tim66@gmail.com");
            mailMessage.setText("You have successfully picked up medicine: " + a.getMedicineName()
                    + "\nAt pharmacy: " +a.getPharmacyName()
                    + "\nAt time: " + formatDateTime);
            emailService.sendEmail(mailMessage);
            return new ResponseEntity<MedicineReservation>(a, HttpStatus.CREATED);
        }

    }

    @PostMapping(value = "/search/{firstname}")
    public Object searchUser(@PathVariable("firstname") String firstname,
                             @RequestBody String lastname, @Context HttpServletRequest request) {

        HttpSession session = request.getSession();
        Pharmacist pharmacist = (Pharmacist) session.getAttribute("pharmacist");

        return pharmacistService.searchUser(firstname, lastname, pharmacist.getId());
    }


    @GetMapping(value = "/patients")
    public Object getMyPatients (@Context HttpServletRequest request) {
        HttpSession session = request.getSession();
        Pharmacist pharmacist = (Pharmacist) session.getAttribute("pharmacist");

        return  pharmacistService.getAllUsers(pharmacist.getId());
    }

    @GetMapping(value = "/reservedAppointments")
    public Object reservedAppointments (@Context HttpServletRequest request) {

        HttpSession session = request.getSession();
        Pharmacist pharmacist = (Pharmacist) session.getAttribute("pharmacist");

        ArrayList<PharmacistAppointmentDTO> appointmentsList =
                pharmacistAppointmentService.getReservedAppointments(pharmacist.getId());

        return appointmentsList;

    }

    @GetMapping(value = "/availableAppointments")
    public Object availableAppointments (@Context HttpServletRequest request) {

        HttpSession session = request.getSession();
        Pharmacist pharmacist = (Pharmacist) session.getAttribute("pharmacist");

        ArrayList<PharmacistAppointmentDTO> appointmentsList =
                pharmacistAppointmentService.getAvailableAppointments(pharmacist.getId());

        return appointmentsList;

    }

    @GetMapping(value = "/appointment/{appointmentId}")
    public Object getAppointemnt (@PathVariable("appointmentId") Long appointmentId) {

        PharmacistAppointmentDTO pharmacistAppointmentDTO = pharmacistAppointmentService.getDTOById(appointmentId);
        return pharmacistAppointmentDTO;
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


        PharmacistAppointment pharmacistAppointment = pharmacistAppointmentService.appointmentReserveForUser(appointmentId,patientId );

        if( pharmacistAppointment != null) {
            emailService.sendEmail(mailMessage);
        }

        return pharmacistAppointment;

    }

    @GetMapping(value = "/reservedAndDoneAppointments")
    public Object reservedAndDoneAppointments (@Context HttpServletRequest request) {

        HttpSession session = request.getSession();
        Pharmacist pharmacist = (Pharmacist) session.getAttribute("pharmacist");

        ArrayList<PharmacistAppointmentDTO> appointmentsList =
                pharmacistAppointmentService.getReservedAndDoneAppointments(pharmacist.getId());

        return appointmentsList;

    }
    }
