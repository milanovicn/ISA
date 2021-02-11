package com.example.ISABackend.repository;

import com.example.ISABackend.model.Rate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;

public interface RateRepository extends JpaRepository<Rate, Long> {
    public ArrayList<Rate> findByPatientId(Long patientId);

}
