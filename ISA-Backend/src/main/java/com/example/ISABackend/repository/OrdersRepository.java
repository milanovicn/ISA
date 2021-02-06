package com.example.ISABackend.repository;

import com.example.ISABackend.model.DermatologistAppointment;
import com.example.ISABackend.model.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;

public interface OrdersRepository extends JpaRepository<Orders, Long> {
    ArrayList<Orders> findByPharmacyId(Long pharmacyId);
}
