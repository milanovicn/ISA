package com.example.ISABackend.service;

import com.example.ISABackend.dto.DermatologistAppointmentDTO;
import com.example.ISABackend.enums.AppointmentStatus;

import com.example.ISABackend.model.DermatologistAppointment;
import com.example.ISABackend.model.DermatologistSchedule;
import com.example.ISABackend.model.Pharmacist;
import com.example.ISABackend.model.User;

import com.example.ISABackend.model.*;

import com.example.ISABackend.repository.DermatologistAppointmentRepository;
import com.example.ISABackend.repository.DermatologistScheduleRepository;
import com.example.ISABackend.repository.PatientPenaltyRepository;
import com.example.ISABackend.repository.PharmacistAppointmentRepository;
import net.bytebuddy.build.BuildLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class DermatologistAppointmentServiceImpl implements DermatologistAppointmentService{

    @Autowired
    private DermatologistAppointmentRepository dermatologistAppointmentRepository;

    @Autowired
    private DermatologistScheduleService dermatologistScheduleService;

    @Autowired
    private PharmacyService pharmacyService;

    @Autowired

    private UserService userService;

    @Autowired
    private DermatologistService dermatologistService;

    @Autowired
    private PatientPenaltyRepository patientPenaltyRepository;

    @Autowired
    private PharmacistAppointmentRepository pharmacistAppointmentRepository;

    @Override
    public List<DermatologistAppointment> getAll() {
        return dermatologistAppointmentRepository.findAll();
    }

    @Override
    public DermatologistAppointment getById(Long id) {
        return dermatologistAppointmentRepository.findById(id).orElseGet(null);
    }

    @Override
    public ArrayList<DermatologistAppointment> getByDermatologist(Long dermatologistId) {
        return dermatologistAppointmentRepository.findByDermatologistId(dermatologistId);
    }

    @Override
    public ArrayList<DermatologistAppointment> getByPharmacy(Long pharmacyId) {
        return dermatologistAppointmentRepository.findByPharmacyId(pharmacyId);
    }

    @Override
    public DermatologistAppointment addDermatologistAppointment(Long pharmacyId, Long dermatologistId, String appointmentTime, Long price, LocalDate appointmentDate) {

        //dodajem 1 dan unapred datumu zato sto bolesnik iz nekog razlog kad salje ssa fronta na bek odzume jedan da od datuma
        appointmentDate = appointmentDate.plusDays(1);
        String appointmentWorkDay = appointmentDate.getDayOfWeek().toString();

        //proveravamo da li je u ovoj apoteci zaposlen ovaj dermatolog na ovaj dan
        ArrayList<DermatologistSchedule> schedule = dermatologistScheduleService.getByDermatologistAndPharmacyAndDay(dermatologistId,pharmacyId, appointmentWorkDay);

        //ako ne radi u toj apoteci na taj dan vrati null
        if(schedule.isEmpty()){
            return null;
        }

        //ako radi u toj apoteci na taj dan proveri da li je slobodan za ovaj datum u ovom terminu
        if(!isAvailable(dermatologistId, appointmentDate, appointmentTime)){
            return null;
        }

        //ako je slobodan kreiraj ga KONACNO
        DermatologistAppointment newDA = new DermatologistAppointment();
        newDA.setDermatologistId(dermatologistId);
        newDA.setDate(appointmentDate);
        newDA.setPrice(price);
        newDA.setPharmacyId(pharmacyId);
        newDA.setTime(appointmentTime);
        newDA.setStatus(AppointmentStatus.AVAILABLE);
        newDA.setPatientId((long) 0);
        newDA.setRated(false);
        dermatologistAppointmentRepository.save(newDA);

        return newDA;
    }

    //vraca true ako ne postoji za tog dermatologa na taj datum i u to vreme vec kreiran termin
    @Override
    public boolean isAvailable(Long dermatologistId, LocalDate appointmentDate, String appointmentTime){
        //uzmi mi preglede ovog dermatologa
        List<DermatologistAppointment> all = this.getByDermatologist(dermatologistId);

        //prodji kroz sve i vidi da li postoji neki kome se poklapaju datum i vreme
        for(DermatologistAppointment da : all){
            if(da.getDate().equals(appointmentDate) && da.getTime().toUpperCase().equals(appointmentTime.toUpperCase())){
                //ako postoji vrati false
                return false;
            }

        }
        //u suprotnom je slobodan
        return true;
    }

    //pravi listu dto objekata za front na osnovu slobodnih termina dermatologa u toj apoteci

    @Override
    public ArrayList<DermatologistAppointmentDTO> getAvailableDermatologistAppointments(Long pharmacyId) {
        ArrayList<DermatologistAppointmentDTO> ret = new ArrayList<DermatologistAppointmentDTO>();
        ArrayList<DermatologistAppointment> byPharmacy = this.getByPharmacy(pharmacyId);

        for(DermatologistAppointment da : byPharmacy){
            //vrati cak i otkazanege
            if(da.getStatus().equals(AppointmentStatus.AVAILABLE) || da.getStatus().equals(AppointmentStatus.CANCELED)){

                Dermatologist derm = dermatologistService.getById(da.getDermatologistId());
                String phName= pharmacyService.getById(pharmacyId).getName();


                //User patient = userService.getById(da.getPatientId());

                String patientName="";
                Long patientId = (long) 0;

               // if(patient!=null){
                //    patientId=patient.getId();
                //    patientName=patient.getFirstName() + " " + patient.getLastName();
                //}

                DermatologistAppointmentDTO toAdd = new DermatologistAppointmentDTO(da.getId(),
                        da.getDermatologistId(),  derm.getFirstName().concat(" ") + derm.getLastName(), derm.getRate(), da.getPharmacyId(),
                        phName, da.getTime(), da.getDate(), da.getPrice(),
                        patientName,patientId, da.getStatus(), da.isRated());

                ret.add(toAdd);

            }

        }


        return ret;
    }

    @Override
    public DermatologistAppointment makeReservation(Long userId, Long appointmentId) {
        //zabrani rezervaciju ako ima 3 penala
        for(PatientPenalty pp : patientPenaltyRepository.findAll()){
            //ako se pp odnosi na korisnika koji pokusava rezervaciju i ima 3 ili vise penala vrati null
            if(userId == pp.getPatientId() && pp.getPenaltyNumber() > 2) {
                return null;
            }
        }
        DermatologistAppointment appointment = this.getById(appointmentId);

        //ako ga je on poslednji otkazao njegov id ce biti na mestu pacijenta
        //u tom slucaju mu ne dozvoljavamo da ga rezervise
        if(userId != appointment.getPatientId()){
            //ako ga nije on otkzao menjamo user id i status u reserved
            appointment.setPatientId(userId);
            appointment.setStatus(AppointmentStatus.RESERVED);
            dermatologistAppointmentRepository.save(appointment);
            return appointment;
        }


        return null;
    }

    //prva
    @Override
    public ArrayList<DermatologistAppointmentDTO> getReservedAppointments(Long id) {

        ArrayList<DermatologistAppointment> listAppointment = getByDermatologist(id);
        ArrayList<DermatologistAppointmentDTO> ret = new ArrayList<>();

        for (DermatologistAppointment i : listAppointment) {
            if (i.getStatus().equals(AppointmentStatus.RESERVED)) {

                String phName= pharmacyService.getById(i.getPharmacyId()).getName();
                User patient = userService.getById(i.getPatientId());

                DermatologistAppointmentDTO toAdd = new DermatologistAppointmentDTO(i.getId(),
                        i.getDermatologistId(), "ime", 1, i.getPharmacyId(),
                        phName, i.getTime(), i.getDate(), i.getPrice(),
                        patient.getFirstName()+ " " +patient.getLastName(),patient.getId(), i.getStatus(),
                        i.isRated());

                ret.add(toAdd);
            }
        }
        return ret;
    }

    //treca
    @Override
    public ArrayList<DermatologistAppointmentDTO> getAvailableAppointments(Long id) {

        ArrayList<DermatologistAppointment> listAppointment = getByDermatologist(id);
        ArrayList<DermatologistAppointmentDTO> ret = new ArrayList<>();

        for (DermatologistAppointment i : listAppointment) {
            if (i.getStatus().equals(AppointmentStatus.AVAILABLE)) {

                String phName= pharmacyService.getById(i.getPharmacyId()).getName();
                User patient = userService.getById(i.getPatientId());

                DermatologistAppointmentDTO toAdd = new DermatologistAppointmentDTO(i.getId(),
                        i.getDermatologistId(), "ime", 1, i.getPharmacyId(),
                        phName, i.getTime(), i.getDate(), i.getPrice(),
                        patient.getFirstName()+ " " +patient.getLastName(),patient.getId(), i.getStatus(),
                        i.isRated());

                ret.add(toAdd);
            }
        }
        return ret;
    }

    //druga
    @Override
    public DermatologistAppointmentDTO getDTOById(Long appointmentId) {


        DermatologistAppointment i = getById(appointmentId);

        String phName= pharmacyService.getById(i.getPharmacyId()).getName();
        User patient = userService.getById(i.getPatientId());

        DermatologistAppointmentDTO toAdd = new DermatologistAppointmentDTO(i.getId(),
                i.getDermatologistId(), "ime", 1, i.getPharmacyId(),
                phName, i.getTime(), i.getDate(), i.getPrice(),
                patient.getFirstName()+ " " +patient.getLastName(),patient.getId(), i.getStatus(), i.isRated());

        return toAdd;
    }


    //cetvrta
    @Override
    public DermatologistAppointment appointmentReserveForUser(Long appointmentId, Long patientId) {

        DermatologistAppointment toReserve = getById(appointmentId);
        ArrayList<DermatologistAppointment> byPatient = dermatologistAppointmentRepository.findByPatientId(patientId);
        ArrayList<PharmacistAppointment> phByPatient = pharmacistAppointmentRepository.findByPatientId(patientId);

        for (DermatologistAppointment i: byPatient) {

            if(i.getDate().equals(toReserve.getDate()) && i.getTime().equals(toReserve.getTime()) && i.getStatus().equals(AppointmentStatus.RESERVED)) {
                return null;
            }
        }

        //odradi isto za proveru kod farmaceuta

        for (PharmacistAppointment i: phByPatient) {

            if(i.getDate().equals(toReserve.getDate()) && i.getTime().equals(toReserve.getTime()) && i.getStatus().equals(AppointmentStatus.RESERVED)) {
                return null;
            }
        }

        toReserve.setPatientId(patientId);
        toReserve.setStatus(AppointmentStatus.RESERVED);
        dermatologistAppointmentRepository.save(toReserve);

        return toReserve;
    }


    public ArrayList<DermatologistAppointmentDTO> getByPatientId(Long patientId) {
        ArrayList<DermatologistAppointmentDTO> ret = new ArrayList<DermatologistAppointmentDTO>();
        ArrayList<DermatologistAppointment> byPatientIdList = dermatologistAppointmentRepository.findByPatientId(patientId);

        for(DermatologistAppointment appointment : byPatientIdList){

            Dermatologist derm = dermatologistService.getById(appointment.getDermatologistId());
            String phName= pharmacyService.getById(appointment.getPharmacyId()).getName();
            User patient = userService.getById(patientId);
            String patientName = patient.getFirstName()+ " "+ patient.getLastName();


            DermatologistAppointmentDTO toAdd = new DermatologistAppointmentDTO(appointment.getId(),
                    appointment.getDermatologistId(), derm.getFirstName().concat(" ") + derm.getLastName(), derm.getRate(),
                    appointment.getPharmacyId(), "pharmacyName", appointment.getTime(), appointment.getDate(), appointment.getPrice(),
                    "patientName", appointment.getId(), appointment.getStatus(), appointment.isRated());


            toAdd.setAppointmentStatus(appointment.getStatus());
            ret.add(toAdd);
        }

        return ret;
    }

    @Override
    public DermatologistAppointment cancelReservation(Long appointmentId) {
        DermatologistAppointment appointment = getById(appointmentId);
        LocalDate now = LocalDate.now();
        // ako je danasnji trenutak nakon 24h pre dana rezervacije ne dozvoli da je otkaze
        if(now.isAfter(appointment.getDate().minusDays(1))){
            return null;
        }
        appointment.setStatus(AppointmentStatus.CANCELED);
        dermatologistAppointmentRepository.save(appointment);

        return appointment;
    }


    @Override
    public ArrayList<Dermatologist> getDermatologistsForPatient(Long patientId) {
        ArrayList<DermatologistAppointment> byPatient = dermatologistAppointmentRepository.findByPatientId(patientId);
        ArrayList<Dermatologist> ret = new ArrayList<>();

        for(DermatologistAppointment da : byPatient){
            if(da.getStatus().equals(AppointmentStatus.DONE) ){
                Dermatologist dermatologist = dermatologistService.getById(da.getDermatologistId());
                if(!ret.contains(dermatologist)){
                ret.add(dermatologist);
                  }
            }
        }


        return ret;
    }

    @Override
    public ArrayList<DermatologistAppointmentDTO> getUnratedDermatologistsAppointments(Long patientId) {
        ArrayList<DermatologistAppointmentDTO> ret = new ArrayList<>();
        for(DermatologistAppointmentDTO da : getByPatientId(patientId)){
            if(!da.isRated()){
                ret.add(da);
            }

        }

        return ret;
    }

    @Override
    public ArrayList<DermatologistAppointmentDTO> getReservedAndDoneAppointments(Long id) {

        ArrayList<DermatologistAppointment> listAppointment = getByDermatologist(id);
        ArrayList<DermatologistAppointmentDTO> ret = new ArrayList<>();

        for (DermatologistAppointment i : listAppointment) {
            if (i.getStatus().equals(AppointmentStatus.RESERVED) || i.getStatus().equals(AppointmentStatus.DONE)) {

                String phName= pharmacyService.getById(i.getPharmacyId()).getName();
                User patient = userService.getById(i.getPatientId());

                DermatologistAppointmentDTO toAdd = new DermatologistAppointmentDTO(i.getId(),
                        i.getDermatologistId(), "ime", 1, i.getPharmacyId(),
                        phName, i.getTime(), i.getDate(), i.getPrice(),
                        patient.getFirstName()+ " " +patient.getLastName(),patient.getId(), i.getStatus(),
                        i.isRated());

                ret.add(toAdd);
            }
        }
        return ret;
    }

    @Override
    public DermatologistAppointment didntShowUp(Long appointmentId) {

        DermatologistAppointment da = getById(appointmentId);
        da.setStatus(AppointmentStatus.DONE);
        userService.addPenalty(da.getPatientId());
        dermatologistAppointmentRepository.save(da);
        return da;
    }


}
