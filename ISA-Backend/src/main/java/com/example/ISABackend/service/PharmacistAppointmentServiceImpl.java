package com.example.ISABackend.service;

import com.example.ISABackend.dto.DermatologistAppointmentDTO;
import com.example.ISABackend.dto.PharmacistAppointmentDTO;
import com.example.ISABackend.enums.AppointmentStatus;
import com.example.ISABackend.model.*;
import com.example.ISABackend.repository.DermatologistAppointmentRepository;
import com.example.ISABackend.repository.PharmacistAppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
@Service
public class PharmacistAppointmentServiceImpl implements PharmacistAppointmentService {

    @Autowired
    private PharmacistAppointmentRepository pharmacistAppointmentRepository;

    @Autowired
    private PharmacistScheduleService pharmacistScheduleService;

    @Autowired
    private PharmacyService pharmacyService;

    // UVEZI DERMATOLOGIST SERVICE DA BI UZELA IME DERMATOLOGA


    @Override
    public List<PharmacistAppointment> getAll() {
        return pharmacistAppointmentRepository.findAll();
    }

    @Override
    public PharmacistAppointment getById(Long id) {
        return pharmacistAppointmentRepository.findById(id).orElseGet(null);
    }

    @Override
    public ArrayList<PharmacistAppointment> getByPharmacist(Long pharmacistId) {
        return pharmacistAppointmentRepository.findByPharmacistId(pharmacistId);
    }

    @Override
    public ArrayList<PharmacistAppointment> getByPharmacy(Long pharmacyId) {
        return pharmacistAppointmentRepository.findByPharmacyId(pharmacyId);
    }

    @Override
    public PharmacistAppointment addPharmacistAppointment(Long pharmacyId, Long pharmacistId, String appointmentTime, Long price, LocalDate appointmentDate) {

        //dodajem 1 dan unapred datumu zato sto bolesnik iz nekog razlog kad salje ssa fronta na bek odzume jedan da od datuma
        appointmentDate = appointmentDate.plusDays(1);
        String appointmentWorkDay = appointmentDate.getDayOfWeek().toString();

        //proveravamo da li je u ovoj apoteci zaposlen ovaj dermatolog na ovaj dan
        ArrayList<PharmacistSchedule> schedule = pharmacistScheduleService.getByPharmacistAndPharmacyAndDay(pharmacistId,pharmacyId, appointmentWorkDay);

        //ako ne radi u toj apoteci na taj dan vrati null
        if(schedule.isEmpty()){
            return null;
        }

        //ako radi u toj apoteci na taj dan proveri da li je slobodan za ovaj datum u ovom terminu
        if(!isAvailable(pharmacistId, appointmentDate, appointmentTime)){
            return null;
        }

        //ako je slobodan kreiraj ga KONACNO
        PharmacistAppointment newDA = new PharmacistAppointment();
        newDA.setPharmacistId(pharmacistId);
        newDA.setDate(appointmentDate);
        newDA.setPrice(price);
        newDA.setPharmacyId(pharmacyId);
        newDA.setTime(appointmentTime);
        newDA.setStatus(AppointmentStatus.AVAILABLE);
        newDA.setPatientId((long) 0);
        pharmacistAppointmentRepository.save(newDA);

        return newDA;
    }

    //vraca true ako ne postoji za tog dermatologa na taj datum i u to vreme vec kreiran termin
    @Override
    public boolean isAvailable(Long pharmacistId, LocalDate appointmentDate, String appointmentTime){
        //uzmi mi preglede ovog dermatologa
        List<PharmacistAppointment> all = this.getByPharmacist(pharmacistId);

        //prodji kroz sve i vidi da li postoji neki kome se poklapaju datum i vreme
        for(PharmacistAppointment da : all){
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
    public ArrayList<PharmacistAppointmentDTO> getAvailableInPharmacy(Long pharmacyId) {
        ArrayList<PharmacistAppointmentDTO> ret = new ArrayList<PharmacistAppointmentDTO>();
        ArrayList<PharmacistAppointment> byPharmacy = this.getByPharmacy(pharmacyId);

        for(PharmacistAppointment da : byPharmacy){
            //vrati cak i otkazane
            if(da.getStatus().equals(AppointmentStatus.AVAILABLE) || da.getStatus().equals(AppointmentStatus.CANCELED)){
                // UVEZI DERMATOLOGIST SERVICE DA BI UZELA IME I OCENU DERMATOLOGA PO ID
                String phName= pharmacyService.getById(pharmacyId).getName();
                PharmacistAppointmentDTO toAdd = new PharmacistAppointmentDTO(da.getId(),
                        da.getPharmacistId(), "ime", 1, da.getPharmacyId(),
                        phName, da.getTime(), da.getDate(), da.getPrice());

                ret.add(toAdd);

            }

        }


        return ret;
    }

    @Override
    public PharmacistAppointment makeReservation(Long userId, Long appointmentId) {
        PharmacistAppointment appointment = this.getById(appointmentId);

        //ako ga je on poslednji otkazao njegov id ce biti na mestu pacijenta
        //u tom slucaju mu ne dozvoljavamo da ga rezervise
        if(userId != appointment.getPatientId()){
            //ako ga nije on otkzao menjamo user id i status u reserved
            appointment.setPatientId(userId);
            appointment.setStatus(AppointmentStatus.RESERVED);
            pharmacistAppointmentRepository.save(appointment);
            return appointment;
        }


        return null;
    }

}
