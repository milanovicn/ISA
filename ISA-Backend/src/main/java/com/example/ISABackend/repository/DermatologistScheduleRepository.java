package com.example.ISABackend.repository;

import com.example.ISABackend.model.Dermatologist;
import com.example.ISABackend.model.DermatologistSchedule;
import com.example.ISABackend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface DermatologistScheduleRepository extends JpaRepository<DermatologistSchedule, Long> {
    ArrayList<DermatologistSchedule> findByDermatologistId(Long dermatologistId);


}
