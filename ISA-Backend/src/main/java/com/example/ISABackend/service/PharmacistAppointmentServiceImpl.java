package com.example.ISABackend.service;

import com.example.ISABackend.dto.DermatologistAppointmentDTO;
import com.example.ISABackend.dto.PharmacistAppointmentDTO;
import com.example.ISABackend.enums.AppointmentStatus;
import com.example.ISABackend.model.*;
import com.example.ISABackend.repository.DermatologistAppointmentRepository;
import com.example.ISABackend.repository.PatientPenaltyRepository;
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

    @Autowired
    private PharmacistService pharmacistService;

    @Autowired
    private PatientPenaltyRepository patientPenaltyRepository;

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
        ArrayList<PharmacistSchedule> schedule = pharmacistScheduleService.getByPharmacistAndPharmacyAndDay(pharmacistId, pharmacyId, appointmentWorkDay);

        //ako ne radi u toj apoteci na taj dan vrati null
        if (schedule.isEmpty()) {
            return null;
        }

        //ako radi u toj apoteci na taj dan proveri da li je slobodan za ovaj datum u ovom terminu
        if (!isAvailable(pharmacistId, appointmentDate, appointmentTime)) {
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
    public boolean isAvailable(Long pharmacistId, LocalDate appointmentDate, String appointmentTime) {
        //uzmi mi preglede ovog dermatologa
        List<PharmacistAppointment> all = this.getByPharmacist(pharmacistId);

        //prodji kroz sve i vidi da li postoji neki kome se poklapaju datum i vreme
        for (PharmacistAppointment da : all) {
            if (da.getDate().equals(appointmentDate) && da.getTime().toUpperCase().equals(appointmentTime.toUpperCase())) {
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

        for (PharmacistAppointment da : byPharmacy) {
            //vrati cak i otkazane
            if (da.getStatus().equals(AppointmentStatus.AVAILABLE) || da.getStatus().equals(AppointmentStatus.CANCELED)) {
                // UVEZI DERMATOLOGIST SERVICE DA BI UZELA IME I OCENU DERMATOLOGA PO ID
                String phName = pharmacyService.getById(pharmacyId).getName();
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
        for(PatientPenalty pp : patientPenaltyRepository.findAll()){
            //ako se pp odnosi na korisnika koji pokusava rezervaciju i ima 3 ili vise penala vrati null
            if(userId == pp.getPatientId() && pp.getPenaltyNumber() > 2) {
                return null;
            }
        }
        PharmacistAppointment appointment = this.getById(appointmentId);

        //ako ga je on poslednji otkazao njegov id ce biti na mestu pacijenta
        //u tom slucaju mu ne dozvoljavamo da ga rezervise
        if (userId != appointment.getPatientId()) {
            //ako ga nije on otkzao menjamo user id i status u reserved
            appointment.setPatientId(userId);
            appointment.setStatus(AppointmentStatus.RESERVED);
            pharmacistAppointmentRepository.save(appointment);
            return appointment;
        }


        return null;
    }

    @Override
    public ArrayList<DermatologistAppointmentDTO> getAvailablePharmacistAppointments(Long pharmacyId) {
        ArrayList<DermatologistAppointmentDTO> ret = new ArrayList<DermatologistAppointmentDTO>();
        ArrayList<PharmacistAppointment> byPharmacy = this.getByPharmacy(pharmacyId);

        for (PharmacistAppointment pa : byPharmacy) {
            //vrati cak i otkazane
            if (pa.getStatus().equals(AppointmentStatus.AVAILABLE) || pa.getStatus().equals(AppointmentStatus.CANCELED)) {

                Pharmacist pharmacist = pharmacistService.getById(pa.getPharmacistId());
                String phName = pharmacyService.getById(pharmacyId).getName();
                DermatologistAppointmentDTO toAdd = new DermatologistAppointmentDTO(pa.getId(),
                        pa.getPharmacistId(), pharmacist.getFirstName().concat(" ") + pharmacist.getLastName(), pharmacist.getRate(), pa.getPharmacyId(),
                        phName, pa.getTime(), pa.getDate(), pa.getPrice(), pa.getStatus());

                ret.add(toAdd);

            }

        }
        return ret;
    }

    // vraca listu svih apoteka koje imaju slobone termine kod farmaceuta u odabrano vreme
    @Override
    public ArrayList<Pharmacy> getPharmaciesByAppointmentDate(String appointmentTime, LocalDate appointmentDate) {
       ArrayList<Pharmacy> ret = new ArrayList<Pharmacy>();

       for(PharmacistAppointment pharmacistAppointment : pharmacistAppointmentRepository.findAll()){
           if(pharmacistAppointment.getDate().equals(appointmentDate.plusDays(1)) && pharmacistAppointment.getTime().equals(appointmentTime) && (pharmacistAppointment.getStatus().equals(AppointmentStatus.AVAILABLE) || pharmacistAppointment.getStatus().equals(AppointmentStatus.CANCELED))){
               ret.add(pharmacyService.getById(pharmacistAppointment.getPharmacyId()));
           }
       }

        return ret;
    }

    @Override
    public ArrayList<DermatologistAppointmentDTO> getByPatientId(Long patientId) {
        ArrayList<DermatologistAppointmentDTO> ret = new ArrayList<DermatologistAppointmentDTO>();
        ArrayList<PharmacistAppointment> byPatientIdList = pharmacistAppointmentRepository.findByPatientId(patientId);

        for(PharmacistAppointment appointment : byPatientIdList){

            Pharmacist pharmacist = pharmacistService.getById(appointment.getPharmacistId());
            String phName= pharmacyService.getById(appointment.getPharmacyId()).getName();
            DermatologistAppointmentDTO toAdd = new DermatologistAppointmentDTO(appointment.getId(),
                    appointment.getPharmacistId(), pharmacist.getFirstName().concat(" ") + pharmacist.getLastName(), pharmacist.getRate(), appointment.getPharmacyId(),
                    phName, appointment.getTime(), appointment.getDate(), appointment.getPrice(), appointment.getStatus());

            ret.add(toAdd);
        }

        return ret;
    }

    @Override
    public PharmacistAppointment cancelReservation(Long appointmentId) {

        PharmacistAppointment appointment = getById(appointmentId);
        LocalDate now = LocalDate.now();
        // ako je danasnji trenutak nakon 24h pre dana rezervacije ne dozvoli da je otkaze
        if(now.isAfter(appointment.getDate().minusDays(1))){
            return null;
        }
        appointment.setStatus(AppointmentStatus.CANCELED);
        pharmacistAppointmentRepository.save(appointment);

        return appointment;
    }

    @Override
    public ArrayList<Pharmacist> getPharmacistsForPatient(Long patientId) {
        ArrayList<PharmacistAppointment> byPatient = pharmacistAppointmentRepository.findByPatientId(patientId);
        ArrayList<Pharmacist> ret = new ArrayList<>();

        for(PharmacistAppointment pa : byPatient){
            if(pa.getStatus().equals(AppointmentStatus.DONE) ){
                Pharmacist pharmacist = pharmacistService.getById(pa.getPharmacistId());
                if(!ret.contains(pharmacist)){
                    ret.add(pharmacist);
                }
            }
        }


        return ret;
    }


}
