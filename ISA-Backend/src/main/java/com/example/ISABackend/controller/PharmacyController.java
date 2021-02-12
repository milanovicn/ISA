package com.example.ISABackend.controller;

import com.example.ISABackend.dto.DateInterval;
import com.example.ISABackend.dto.SearchPharmacy;
import com.example.ISABackend.enums.WorkDays;
import com.example.ISABackend.model.*;
import com.example.ISABackend.repository.PharmacyRepository;
import com.example.ISABackend.service.*;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

import javax.mail.Session;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.core.Context;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Properties;

@RestController
@RequestMapping("/api/pharmacy")

public class PharmacyController {

    @Autowired
    private ActionsService actionsService;

    @Autowired
    private EmailService emailService;
    @Autowired
    private PharmacyRepository pharmacyRepository;

    @Autowired
    private ActionPharmacyUserService actionPharmacyUserService;

    @Autowired
    private PharmacyService pharmacyService;

    @Autowired
    private PharmacyStockService pharmacyStockService;

    @Autowired
    private DermatologistAppointmentService dermatologistAppointmentService;

    @Autowired
    private PharmacistAppointmentService pharmacistAppointmentService;

    @Autowired
    private UserService userService;

    @Autowired
    private MedicinePriceService medicinePriceService;

    @PutMapping(value = "/editPharmacy")
    public ResponseEntity updateUser(@RequestBody Pharmacy updatedPharmacy, @Context HttpServletRequest request) {
        if (authorize(request) == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        Pharmacy pharmacy = pharmacyService.updatePharmacy(updatedPharmacy);
        //request.getSession().setAttribute("pharmacy", pharmacy);
        return new ResponseEntity<Pharmacy>(pharmacy, HttpStatus.CREATED);
    }

    @GetMapping(value = "")
    public Object getAll() {

        return pharmacyService.findAll();
    }

    @PostMapping(value = "/search")
    @Transactional(readOnly = true)
    public Object searchPharmacy(@RequestBody SearchPharmacy searchParameters) {

        return pharmacyService.search(searchParameters);
    }

    @PostMapping(value = "/sort/{sortType}")
    public Object sortPharmacy(@RequestBody ArrayList<Pharmacy> sortPharmacies, @PathVariable("sortType") String sortType) {

        return pharmacyService.sort(sortPharmacies, sortType);
    }


    private Pharmacy_Admin authorize(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Pharmacy_Admin pa = (Pharmacy_Admin) session.getAttribute("pharmacy_admin");
        return pa;
    }

    @GetMapping(value = "/mydermas/{pharmacyId}")
    public Object getMyDerma(@PathVariable("pharmacyId") Long pharmacyId, @Context HttpServletRequest request) {
        if (authorize(request) == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        return pharmacyService.getDermatologists(pharmacyId);
    }

    @GetMapping(value = "/medicinesInStock/{pharmacyId}")
    public Object medicinesInStock(@PathVariable("pharmacyId") Long pharmacyId, @Context HttpServletRequest request) {
        if (authorize(request) == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        return pharmacyStockService.getPharmacyStock(pharmacyId);
    }

    @GetMapping(value = "/mymedicine/{pharmacyId}")
    public Object getMyMedicine(@PathVariable("pharmacyId") Long pharmacyId, @Context HttpServletRequest request) {
        if (authorize(request) == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        return pharmacyStockService.getMedicineInStock(pharmacyId);
    }


    @GetMapping(value = "/mypharmas/{pharmacyId}")
    public Object getMyPharma(@PathVariable("pharmacyId") Long pharmacyId, @Context HttpServletRequest request) {
        if (authorize(request) == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        return pharmacyService.getPharmacists(pharmacyId);
    }


    @PutMapping(value = "/addmedicine/{medicine_id}")
    public ResponseEntity<?> addNewMedicine(@RequestBody Pharmacy updatedUser, @PathVariable("medicine_id") Long medicine_id, @Context HttpServletRequest request) {
        if (authorize(request) == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        Pharmacy u = pharmacyService.addMedicine(updatedUser.getId(), medicine_id);
        if (u == null) {
            return new ResponseEntity<String>("This medicine is already added!", HttpStatus.METHOD_NOT_ALLOWED);
        }
        return new ResponseEntity<Pharmacy>(u, HttpStatus.CREATED);
    }

    //vraca dermatologe koji nisu zaposleni u ovoj apoteci
    @GetMapping(value = "/availableDermatologists/{pharmacyId}")
    public Object getAvailableDermatologists(@PathVariable("pharmacyId") Long pharmacyId, @Context HttpServletRequest request) {
        if (authorize(request) == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        return pharmacyService.getAvailableDermatologists(pharmacyId);
    }

    //zaposljava dermatologa u apoteku ako je slobodan na sve odabrane dane
    @PutMapping(value = "/addDermatologist/{pharmacyId}/{dermatologistId}")
    public Object addDermatologist(@PathVariable("pharmacyId") Long pharmacyId, @PathVariable("dermatologistId") Long dermatologistId, @RequestBody ArrayList<WorkDays> workDays, @Context HttpServletRequest request) {
        if (authorize(request) == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        return pharmacyService.scheduleDermatologist(pharmacyId, dermatologistId, workDays);
    }

    @GetMapping(value = "/{pharmacyId}")
    public Object getMyMedicine(@PathVariable("pharmacyId") Long pharmacyId) {

        return pharmacyService.getById(pharmacyId);
    }

    @PostMapping(value = "/addMedicineToStock/{pharmacyId}/{medicineId}")
    public Object addMedicineToStock(@PathVariable("pharmacyId") Long pharmacyId, @PathVariable("medicineId") Long medicineId, @RequestBody int quantity, @Context HttpServletRequest request) {
        if (authorize(request) == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        Long id = pharmacyStockService.addNewMedicineInStock(pharmacyId, medicineId, quantity);
        return new ResponseEntity<Long>(id, HttpStatus.CREATED);
    }


    @GetMapping(value = "/availableDermatologistAppointments/{pharmacyId}")
    public Object availableDermatologistAppointments(@PathVariable("pharmacyId") Long pharmacyId) {

        return dermatologistAppointmentService.getAvailableDermatologistAppointments(pharmacyId);
    }

    @GetMapping(value = "/availablePharmacistAppointments/{pharmacyId}")
    public Object availablePharmacistAppointments(@PathVariable("pharmacyId") Long pharmacyId) {

        return pharmacistAppointmentService.getAvailablePharmacistAppointments(pharmacyId);
    }

    //zaposljava farmaceuta u apoteku ako je slobodan na sve odabrane dane
    @PutMapping(value = "/addPharmacist/{pharmacyId}/{pharmacistId}")
    public Object addPharmacist(@PathVariable("pharmacyId") Long pharmacyId, @PathVariable("pharmacistId") Long pharmacistId, @RequestBody ArrayList<WorkDays> workDays, @Context HttpServletRequest request) {
        if (authorize(request) == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        return pharmacyService.schedulePharmacist(pharmacyId, pharmacistId, workDays);
    }

    @GetMapping(value = "/myactions/{pharmacyId}")
    public Object getMyActions(@PathVariable("pharmacyId") Long pharmacyId, @Context HttpServletRequest request) {
        if (authorize(request) == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        return actionsService.getPharmacyActions(pharmacyId);
    }

    @PostMapping(value = "/newAction/{pharmacyId}")
    public Object addNewAction(@RequestBody Actions newAction, @PathVariable("pharmacyId") Long pharmacyId, @Context HttpServletRequest request) {
        if (authorize(request) == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        Actions a = actionsService.addNewAction(newAction, pharmacyId);

        //nasla id apoteke
        ArrayList<ActionPharmacyUser> svi = actionPharmacyUserService.getByPharmacy(pharmacyId);

        //svi korisnici koji su pretplaceni na tu apoteku
        for (ActionPharmacyUser prolazim : svi) {
            //Za svaki entitet po idpacijenta pronadjem
            // pacijenta u pacijent servisu i odatle mu
            //  uzmem mail i na taj mail posaljem

            if(prolazim.getUserId()!=0) {

                User neko = userService.getById(prolazim.getUserId());
                Properties props = new Properties();
                props.put("mail.mime.address.strict", "false");
                props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
                Session session = Session.getDefaultInstance(props);
                SimpleMailMessage mailMessage = new SimpleMailMessage();
                mailMessage.setTo(neko.getEmail());
                mailMessage.setSubject("New action in our Pharmacy!");
                mailMessage.setFrom("ISA.tim66@gmail.com");
                mailMessage.setText("New actions in our pharmacy is:  " + a.getDescription());
                emailService.sendEmail(mailMessage);
            }
        }
        return a;
    }


    @DeleteMapping(value = "/{medicineId}")
    public ResponseEntity<Medicine> deleteMedicine(@PathVariable("medicineId") Long medicineId, HttpServletRequest request) {
        HttpSession session = request.getSession();
        Pharmacy_Admin d = (Pharmacy_Admin) session.getAttribute("pharmacy_admin");
        pharmacyService.deleteMedicine(d.getPharmacy().getId(), medicineId);
        return new ResponseEntity<Medicine>(HttpStatus.NO_CONTENT);
    }


    @PostMapping(value = "/pharmacyByTime/{appointmentTime}")
    public Object findPharmacyByTime(@RequestBody LocalDate appointmentDate, @PathVariable("appointmentTime") String appointmentTime, @Context HttpServletRequest request) {

        ArrayList<Pharmacy> pharmacies = pharmacistAppointmentService.getPharmaciesByAppointmentDate(appointmentTime, appointmentDate);

        return new ResponseEntity<ArrayList<Pharmacy>>(pharmacies, HttpStatus.ACCEPTED);
    }

    @GetMapping(value = "/myprices/{pharmacyId}")
    public Object getMyPrices(@PathVariable("pharmacyId") Long pharmacyId, @Context HttpServletRequest request) {
        if (authorize(request) == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        return medicinePriceService.getMedicinePrice(pharmacyId);
    }

    @PostMapping(value = "/newPrice/{pharmacyId}/{medicineId}/{price}")
    public Object addNewPrice(@PathVariable("pharmacyId") Long pharmacyId,
                              @PathVariable("medicineId") Long medicineId,
                              @PathVariable("price") Long price,
                              @RequestBody DateInterval dateInterval,
                              @Context HttpServletRequest request) {


        if (authorize(request) == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        MedicinePrice a = medicinePriceService.addNewPrice(pharmacyId, medicineId, dateInterval, price);

        if (a == null) {
            return new ResponseEntity<String>("You have already added price for this medicine", HttpStatus.ACCEPTED);
        }
        // return medicinePriceService.addNewPrice(pharmacyId, medicineId,dateFrom,dateTo,price);
        return new ResponseEntity<MedicinePrice>(a, HttpStatus.ACCEPTED);

    }

}