package com.example.ISABackend.service;

import com.example.ISABackend.model.OrderItem;
import com.example.ISABackend.model.OrderOffer;
import com.example.ISABackend.model.Orders;

import java.time.LocalDate;
import java.util.ArrayList;

public interface OrdersService {
    public ArrayList<Orders> getActiveOrders();
    public ArrayList<OrderItem> getItemsByOrderId(Long orderId);
    public OrderOffer createOffer(Long orderId, Long price, LocalDate deliveryDate, Long supplierId);
    public Orders getOrderById(Long orderId);
    public OrderOffer findOffer(Long orderId, Long supplierId);
    public ArrayList<OrderOffer> myOffers(Long supplierId);
    public OrderOffer acceptOffer(Long offerId);
    public ArrayList<Orders> getOrdersByPharmacy(Long pharmacyId);
    public Boolean checkAdmin(Long offerId, Long adminId);
    public Boolean checkDeadline(Long offerId);
    public ArrayList<OrderOffer> getOffersByOrder(Long orderId);
    public Orders deleteOrder(Long orderId);
}
