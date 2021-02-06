package com.example.ISABackend.repository;

import com.example.ISABackend.model.OrderItem;
import com.example.ISABackend.model.OrderOffer;
import com.example.ISABackend.model.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;

public interface OrderOfferRepository  extends JpaRepository<OrderOffer, Long> {
    ArrayList<OrderOffer> findByOrderId(Long orderId);
}
