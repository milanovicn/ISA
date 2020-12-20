package com.example.ISABackend.repository;

import com.example.ISABackend.model.Dermatologist;
import com.example.ISABackend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DermatologistRepository  extends JpaRepository<Dermatologist, Long> {
}
