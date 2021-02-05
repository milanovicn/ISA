package com.example.ISABackend.repository;

import com.example.ISABackend.model.OrderItem;
import com.example.ISABackend.model.OrderOffer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderOfferRepository  extends JpaRepository<OrderOffer, Long> {
}
