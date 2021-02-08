package com.example.ISABackend.controller;

import com.example.ISABackend.dto.DermatologistAppointmentDTO;
import com.example.ISABackend.model.*;
import com.example.ISABackend.repository.ConfirmationTokenRepository;
import com.example.ISABackend.repository.UserRepository;
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
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private ConfirmationTokenRepository confirmationTokenRepository;

    @Autowired
    private EmailService emailService;

    @Autowired
    private DermatologistAppointmentService dermatologistAppointmentService;

    @Autowired
    private PharmacistAppointmentService pharmacistAppointmentService;

    @Autowired
    private MedicineReservationService medicineReservationService;

    @Autowired
    private ActionsService actionsService;

    @PostMapping(value = "/register")
    public ResponseEntity registerUser(@RequestBody User newUser, @Context HttpServletRequest request) {

        User user = userService.registerUser(newUser);
        if(user != null) {
            Properties props = new Properties();
            props.put("mail.mime.address.strict", "false");
            props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
            Session session = Session.getDefaultInstance(props);
            ConfirmationToken confirmationToken = new ConfirmationToken(user);

            confirmationTokenRepository.save(confirmationToken);

            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setTo(user.getEmail());
            mailMessage.setSubject("Complete Registration!");
            mailMessage.setFrom("ISA.tim66@gmail.com");
            mailMessage.setText("To confirm your account, please click here : "
                    +"http://localhost:8080/api/user/confirm-account?token="+confirmationToken.getConfirmationToken());

            emailService.sendEmail(mailMessage);


            return new ResponseEntity<User>(user, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<String>("User with email is already registered", HttpStatus.METHOD_NOT_ALLOWED);
        }
    }

    @RequestMapping(value="/confirm-account", method= {RequestMethod.GET, RequestMethod.POST})
    public ResponseEntity confirmUserAccount(@RequestParam("token")String confirmationToken, @Context HttpServletRequest request)
    {
        ConfirmationToken token = confirmationTokenRepository.findByConfirmationToken(confirmationToken);

        if(token != null)
        {
            User user = userRepository.findByEmail(token.getUserEntity().getEmail());
            user.setPrviPutLogovan(false);
            userRepository.save(user);
            request.getSession().setAttribute("user", user);
            return new ResponseEntity<String>("Registration successful", HttpStatus.CREATED);
        }
        else
        {
            return new ResponseEntity<String>("Registration not successful", HttpStatus.METHOD_NOT_ALLOWED);
        }


    }


    @PutMapping(value = "/edit")
    public ResponseEntity updateUser(@RequestBody User updatedUser, @Context HttpServletRequest request) {
        if(authorize(request) == null ) {
            return new ResponseEntity<>( HttpStatus.UNAUTHORIZED);
        }
        User user=userService.updateUser(updatedUser);
        request.getSession().setAttribute("user", user);
        return new ResponseEntity<User>(user, HttpStatus.CREATED);
    }

    @GetMapping(value = "/allergies/{user_id}")
    public Object getMyAllergies(@PathVariable("user_id") Long user_id, @Context HttpServletRequest request) {
        if(authorize(request) == null ) {
            return new ResponseEntity<>( HttpStatus.UNAUTHORIZED);
        }
      return  userService.getById(user_id).getAllergies();
    }


    @PutMapping(value = "/allergy/{medicine_id}")
    public ResponseEntity<?> addNewAllergy(@RequestBody User updatedUser, @PathVariable("medicine_id") Long medicine_id, @Context HttpServletRequest request) {
        if(authorize(request) == null ) {
            return new ResponseEntity<>( HttpStatus.UNAUTHORIZED);
        }

        User u = userService.addAllergy(updatedUser.getId(), medicine_id);
        if (u == null) {
            return new ResponseEntity<String>("This allergy is already added!", HttpStatus.METHOD_NOT_ALLOWED);
        }
        return new ResponseEntity<User>(u, HttpStatus.CREATED);
    }

    @PutMapping(value = "/makeDermatologistAppointment/{appointmentId}")
    public ResponseEntity<?> makeDermatologistAppointment(@RequestBody User user, @PathVariable("appointmentId") Long appointmentId, @Context HttpServletRequest request) {
        if(authorize(request) == null ) {
            return new ResponseEntity<>( HttpStatus.UNAUTHORIZED);
        }

        DermatologistAppointment app = dermatologistAppointmentService.makeReservation(user.getId(), appointmentId);
        if (app == null) {
            return new ResponseEntity<String>("Unsuccessful! You are not allowed to make this reservation!", HttpStatus.METHOD_NOT_ALLOWED);
        } else  {

            Properties props = new Properties();
            props.put("mail.mime.address.strict", "false");
            props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
            Session session = Session.getDefaultInstance(props);

            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setTo(user.getEmail());
            mailMessage.setSubject("Dermatologist's appointment is reserved!");
            mailMessage.setFrom("ISA.tim66@gmail.com");
            mailMessage.setText("You have successfully made the reservation at  " + app.getTime() +", " +app.getDate());

            emailService.sendEmail(mailMessage);


            return new ResponseEntity<Long>(app.getId(), HttpStatus.CREATED);
        }
        
    }

    @PutMapping(value = "/makePharmacistAppointment/{appointmentId}")
    public ResponseEntity<?> makePharmacistAppointment(@RequestBody User user, @PathVariable("appointmentId") Long appointmentId, @Context HttpServletRequest request) {
        if(authorize(request) == null ) {
            return new ResponseEntity<>( HttpStatus.UNAUTHORIZED);
        }

        PharmacistAppointment app = pharmacistAppointmentService.makeReservation(user.getId(), appointmentId);
        if (app == null) {
            return new ResponseEntity<String>("Unsuccessful! You are not allowed to make this reservation!", HttpStatus.METHOD_NOT_ALLOWED);
        } else  {

            Properties props = new Properties();
            props.put("mail.mime.address.strict", "false");
            props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
            Session session = Session.getDefaultInstance(props);

            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setTo(user.getEmail());
            mailMessage.setSubject("Pharmacist's counseling is reserved!");
            mailMessage.setFrom("ISA.tim66@gmail.com");
            mailMessage.setText("You have successfully made the reservation at  " + app.getTime() +", " +app.getDate());

            emailService.sendEmail(mailMessage);


            return new ResponseEntity<Long>(app.getId(), HttpStatus.CREATED);
        }

    }


    @GetMapping(value = "/getMyDermatologistAppointments/{patientId}")
    public Object getMyDermatologistAppointments(@PathVariable("patientId") Long patientId, @Context HttpServletRequest request) {
        if(authorize(request) == null ) {
            return new ResponseEntity<>( HttpStatus.UNAUTHORIZED);
        }
        return  dermatologistAppointmentService.getByPatientId(patientId);
    }

    @GetMapping(value = "/getMyPharmacistAppointments/{patientId}")
    public Object getMyPharmacistAppointments(@PathVariable("patientId") Long patientId, @Context HttpServletRequest request) {
        if(authorize(request) == null ) {
            return new ResponseEntity<>( HttpStatus.UNAUTHORIZED);
        }
        return  pharmacistAppointmentService.getByPatientId(patientId);
    }

    @GetMapping(value = "/cancelDermatologistAppointment/{appointmentId}")
    public ResponseEntity<?> cancelDermatologistAppointment(@PathVariable("appointmentId") Long appointmentId, @Context HttpServletRequest request) {
        if(authorize(request) == null ) {
            return new ResponseEntity<>( HttpStatus.UNAUTHORIZED);
        }

        DermatologistAppointment app = dermatologistAppointmentService.cancelReservation(appointmentId);
        if (app == null) {
            return new ResponseEntity<Object>(null, HttpStatus.ACCEPTED);
        } else  {
            return new ResponseEntity<DermatologistAppointment>(app, HttpStatus.CREATED);
        }
    }

    @GetMapping(value = "/cancelPharmacistAppointment/{appointmentId}")
    public ResponseEntity<?> cancelPharmacistAppointment(@PathVariable("appointmentId") Long appointmentId, @Context HttpServletRequest request) {
        if(authorize(request) == null ) {
            return new ResponseEntity<>( HttpStatus.UNAUTHORIZED);
        }

        PharmacistAppointment app = pharmacistAppointmentService.cancelReservation(appointmentId);
        if (app == null) {
            return new ResponseEntity<Object>(null, HttpStatus.ACCEPTED);
        } else  {
            return new ResponseEntity<PharmacistAppointment>(app, HttpStatus.CREATED);
        }
    }



    @PostMapping(value = "/sort/{sortType}")
    public Object sortPharmacy(@RequestBody ArrayList<DermatologistAppointmentDTO> sortAppointments, @PathVariable("sortType") String sortType) {

        return userService.sort(sortAppointments, sortType);
    }

    @GetMapping(value = "/cancelMedicineReservation/{reservationId}")
    public ResponseEntity<?> cancelMedicineReservation(@PathVariable("reservationId") Long reservationId, @Context HttpServletRequest request) {
        if(authorize(request) == null ) {
            return new ResponseEntity<>( HttpStatus.UNAUTHORIZED);
        }

        MedicineReservation app = medicineReservationService.cancelReservation(reservationId);
        if (app == null) {
            return new ResponseEntity<Object>(null, HttpStatus.ACCEPTED);
        } else  {
            return new ResponseEntity<MedicineReservation>(app, HttpStatus.CREATED);
        }
    }

    @PostMapping(value = "/medicineReservation")
    public Object medicineReservation(@RequestBody MedicineReservation newReservation, @Context HttpServletRequest request) {
        if(authorize(request) == null ) {
            return new ResponseEntity<>( HttpStatus.UNAUTHORIZED);
        }
        MedicineReservation app = medicineReservationService.makeReservation(newReservation);

        if (app == null) {
            return new ResponseEntity<Object>(null, HttpStatus.ACCEPTED);
        } else  {
            HttpSession sessionUsr = request.getSession();
            User user = (User) sessionUsr.getAttribute("user");
            Properties props = new Properties();
            props.put("mail.mime.address.strict", "false");
            props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
            Session session = Session.getDefaultInstance(props);

            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setTo(user.getEmail());
            mailMessage.setSubject("Medicine is reserved!");
            mailMessage.setFrom("ISA.tim66@gmail.com");
            mailMessage.setText("You have successfully made the reservation of a medicine: " + app.getMedicineName()
                    + ", at pharmacy" +app.getPharmacyName() + ". RESERVATION IDENTIFICATION NUMBER: " + app.getId());

            return new ResponseEntity<MedicineReservation>(app, HttpStatus.CREATED);
        }
    }

    @GetMapping(value = "/getAvailablePharmacies/{medicineId}")
    public ResponseEntity<?> getAvailablePharmacies(@PathVariable("medicineId") Long medicineId, @Context HttpServletRequest request) {
        if(authorize(request) == null ) {
            return new ResponseEntity<>( HttpStatus.UNAUTHORIZED);
        }

        ArrayList<Pharmacy> app = medicineReservationService.getPharmaciesWithAvailableMedicine(medicineId);
        if (app == null) {
            return new ResponseEntity<Object>(null, HttpStatus.ACCEPTED);
        } else  {
            return new ResponseEntity<ArrayList<Pharmacy>>(app, HttpStatus.CREATED);
        }
    }

    @GetMapping(value = "/medicineReservation/{patientId}")
    public ResponseEntity<?> getMyMedicineReservation(@PathVariable("patientId") Long patientId, @Context HttpServletRequest request) {
        if(authorize(request) == null ) {
            return new ResponseEntity<>( HttpStatus.UNAUTHORIZED);
        }

        ArrayList<MedicineReservation> app = medicineReservationService.getByPatientId(patientId);
        if (app == null) {
            return new ResponseEntity<Object>(null, HttpStatus.ACCEPTED);
        } else  {
            return new ResponseEntity<ArrayList<MedicineReservation>>(app, HttpStatus.CREATED);
        }
    }

    @GetMapping(value = "/patientPenalty/{patientId}")
    public ResponseEntity<?> getMyPatientPenalty(@PathVariable("patientId") Long patientId, @Context HttpServletRequest request) {
        if(authorize(request) == null ) {
            return new ResponseEntity<>( HttpStatus.UNAUTHORIZED);
        }

        Long app = userService.getMyPenalty(patientId);
        if (app == null) {
            return new ResponseEntity<Object>(null, HttpStatus.ACCEPTED);
        } else  {
            return new ResponseEntity<Long>(app, HttpStatus.CREATED);
        }
    }

    @GetMapping(value = "/getSubscriptionPharmacies/{patientId}")
    public ResponseEntity<?> getSubscriptionPharmacies(@PathVariable("patientId") Long patientId, @Context HttpServletRequest request) {
        if(authorize(request) == null ) {
            return new ResponseEntity<>( HttpStatus.UNAUTHORIZED);
        }

        ArrayList<Pharmacy> app = actionsService.getPharmaciesByPatientId(patientId);
        if (app == null) {
            return new ResponseEntity<Object>(null, HttpStatus.ACCEPTED);
        } else  {
            return new ResponseEntity<ArrayList<Pharmacy>>(app, HttpStatus.CREATED);
        }
    }

    @GetMapping(value = "/isSubscribedToAction/{patientId}/{pharmacyId}")
    public ResponseEntity<?> isSubscribedToAction(@PathVariable("patientId") Long patientId, @PathVariable("pharmacyId") Long pharmacyId, @Context HttpServletRequest request) {
        if(authorize(request) == null ) {
            return new ResponseEntity<>( HttpStatus.UNAUTHORIZED);
        }

        Boolean app = actionsService.isSubscribed(patientId, pharmacyId);

            return new ResponseEntity<Boolean>(app, HttpStatus.CREATED);

    }

    @GetMapping(value = "/subscribeToAction/{patientId}/{pharmacyId}")
    public ResponseEntity<?> subscribeToAction(@PathVariable("patientId") Long patientId, @PathVariable("pharmacyId") Long pharmacyId, @Context HttpServletRequest request) {
        if(authorize(request) == null ) {
            return new ResponseEntity<>( HttpStatus.UNAUTHORIZED);
        }

        Boolean app = actionsService.subscribeToAction(patientId, pharmacyId);

        return new ResponseEntity<Boolean>(app, HttpStatus.CREATED);

    }


    private User authorize(HttpServletRequest request){
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        return user;
    }



}
