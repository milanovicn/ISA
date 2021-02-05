package com.example.ISABackend.service;

import com.example.ISABackend.dto.DermatologistAppointmentDTO;
import com.example.ISABackend.enums.AppointmentStatus;
import com.example.ISABackend.model.Dermatologist;
import com.example.ISABackend.model.DermatologistAppointment;
import com.example.ISABackend.model.DermatologistSchedule;
import com.example.ISABackend.repository.DermatologistAppointmentRepository;
import com.example.ISABackend.repository.DermatologistScheduleRepository;
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
    private DermatologistService dermatologistService;


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
        newDA.setStatus(AppointmentStatus.RESERVED);
        newDA.setPatientId((long) 0);
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
    public ArrayList<DermatologistAppointmentDTO> getAvailableInPharmacy(Long pharmacyId) {
        ArrayList<DermatologistAppointmentDTO> ret = new ArrayList<DermatologistAppointmentDTO>();
        ArrayList<DermatologistAppointment> byPharmacy = this.getByPharmacy(pharmacyId);

        for(DermatologistAppointment da : byPharmacy){
            //vrati cak i otkazane
            if(da.getStatus().equals(AppointmentStatus.AVAILABLE) || da.getStatus().equals(AppointmentStatus.CANCELED)){

                Dermatologist derm = dermatologistService.getById(da.getDermatologistId());
                String phName= pharmacyService.getById(pharmacyId).getName();
                DermatologistAppointmentDTO toAdd = new DermatologistAppointmentDTO(da.getId(),
                        da.getDermatologistId(), derm.getFirstName().concat(" ") + derm.getLastName(), derm.getRate(), da.getPharmacyId(),
                        phName, da.getTime(), da.getDate(), da.getPrice());

                ret.add(toAdd);

            }

        }


        return ret;
    }

    @Override
    public DermatologistAppointment makeReservation(Long userId, Long appointmentId) {
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

}
