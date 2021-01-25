package com.example.ISABackend.repository;

import com.example.ISABackend.model.SystemAdmin;
import com.example.ISABackend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SystemAdminRepository extends JpaRepository<SystemAdmin, Long> {
    SystemAdmin findByEmail(String email);

}
