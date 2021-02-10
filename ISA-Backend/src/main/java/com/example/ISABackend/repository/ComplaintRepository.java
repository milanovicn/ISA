package com.example.ISABackend.repository;

import com.example.ISABackend.model.Complaint;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ComplaintRepository extends JpaRepository<Complaint, Long> {

}
