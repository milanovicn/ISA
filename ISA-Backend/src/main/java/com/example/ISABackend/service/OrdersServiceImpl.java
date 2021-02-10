package com.example.ISABackend.service;

import com.example.ISABackend.enums.OrderOfferStatus;
import com.example.ISABackend.enums.OrderStatus;
import com.example.ISABackend.model.*;
import com.example.ISABackend.repository.OrderItemRepository;
import com.example.ISABackend.repository.OrderOfferRepository;
import com.example.ISABackend.repository.OrdersRepository;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;

@Service
public class OrdersServiceImpl implements OrdersService {

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private OrderOfferRepository orderOfferRepository;

    @Autowired
    private OrdersRepository ordersRepository;

    @Autowired
    private PharmacyStockService pharmacyStockService;


    @Override
    public ArrayList<Orders> getActiveOrders() {
        ArrayList<Orders> ret = new ArrayList<Orders>();

        for (Orders o : ordersRepository.findAll()) {
            if (o.getOrderStatus().equals(OrderStatus.ACTIVE)) {
                ret.add(o);
            }
        }

        return ret;
    }

    @Override
    public ArrayList<OrderItem> getItemsByOrderId(Long orderId) {
        ArrayList<OrderItem> ret = new ArrayList<OrderItem>();

        for (OrderItem o : orderItemRepository.findAll()) {
            if (o.getOrderId() == orderId) {
                ret.add(o);
            }
        }

        return ret;
    }

    //pravi novu ako nije taj suplajer vec dao ponudu, ako jeste onda je samo izmeni pazeci da nije istekao datum narudzbenice
    @Override
    public OrderOffer createOffer(Long orderId, Long price, LocalDate deliveryDate, Long supplierId) {
        Orders order = ordersRepository.findById(orderId).orElseGet(null);
        OrderOffer orderOffer = findOffer(orderId, supplierId);
        if (orderOffer == null) {
            OrderOffer offer = new OrderOffer();
            offer.setPrice(price);
            offer.setDeliveryDate(deliveryDate);
            offer.setOrderId(orderId);
            offer.setSupplierId(supplierId);
            offer.setStatus(OrderOfferStatus.PENDING);
            orderOfferRepository.save(offer);
            return offer;
        } else if(order.getDeadline().isBefore(LocalDate.now())){
            return null;
        } else{
            orderOffer.setPrice(price);
            orderOffer.setDeliveryDate(deliveryDate);
            orderOfferRepository.save(orderOffer);
            return orderOffer;
        }

    }

    @Override
    public Orders getOrderById(Long orderId) {
        return ordersRepository.findById(orderId).orElseGet(null);
    }

    @Override
    public OrderOffer findOffer(Long orderId, Long supplierId) {
        for (OrderOffer orderOffer : orderOfferRepository.findAll()) {
            if (orderOffer.getSupplierId() == supplierId && orderOffer.getOrderId() == orderId) {
                return orderOffer;
            }
        }
        return null;
    }

    @Override
    public ArrayList<OrderOffer> myOffers(Long supplierId) {
        ArrayList<OrderOffer> myOffers = new ArrayList<OrderOffer>();
        for (OrderOffer orderOffer: orderOfferRepository.findAll() ) {
            if(orderOffer.getSupplierId()==supplierId){
                myOffers.add(orderOffer);
            }
        }

        return myOffers;
    }

    @Override
    public OrderOffer acceptOffer(Long offerId) {
        //prihvata ponudu
        OrderOffer toAccept = orderOfferRepository.findById(offerId).orElseGet(null);
        toAccept.setStatus(OrderOfferStatus.ACCEPTED);
        orderOfferRepository.save(toAccept);

        //odbija ostale
        ArrayList<OrderOffer> byOrderId = orderOfferRepository.findByOrderId(toAccept.getOrderId());

        for(OrderOffer oo : byOrderId){
            if(oo.getId()!=offerId){
                oo.setStatus(OrderOfferStatus.REJECTED);
                orderOfferRepository.save(oo);
            }

        }

        //dodaje lekove na stanje
        pharmacyStockService.addMedicinesFromOrder(toAccept.getOrderId());

        //menja stanje narudzbenice
        Orders order = ordersRepository.findById(toAccept.getOrderId()).orElseGet(null);
        order.setOrderStatus(OrderStatus.INACTIVE);
        ordersRepository.save(order);

        return toAccept;
    }

    //vraca false ako ulogovani admin nije kreirao narudzbenicu
    @Override
    public Boolean checkAdmin(Long offerId, Long adminId){
        OrderOffer toAccept = orderOfferRepository.findById(offerId).orElseGet(null);
        Orders order = ordersRepository.findById(toAccept.getOrderId()).orElseGet(null);
        if(adminId != order.getPharmacyAdminId()){
            return false;
        }
        return true;
    }

    //vraca false ako nije jos istekao rok za davanje ponuda za narudzbenicu
    @Override
    public Boolean checkDeadline(Long offerId) {
        OrderOffer toAccept = orderOfferRepository.findById(offerId).orElseGet(null);
        Orders order = ordersRepository.findById(toAccept.getOrderId()).orElseGet(null);
        if(LocalDate.now().isBefore(order.getDeadline())){
            return false;
        }
        return true;
    }

    @Override
    public ArrayList<OrderOffer> getOffersByOrder(Long orderId) {


        return orderOfferRepository.findByOrderId(orderId);
    }

    //pharmacy adminu prikazuje sve narudzbenice njegove apoteke koje niju obrisane
    @Override
    public ArrayList<Orders> getOrdersByPharmacy(Long pharmacyId) {
          ArrayList<Orders>  ret =  new  ArrayList<Orders>();

        for (Orders o : ordersRepository.findByPharmacyId(pharmacyId)  ) {
            if(o.getOrderStatus() != OrderStatus.DELETED){
                ret.add(o);
            }
        }
        return ret;
    }

    @Override
    public ArrayList<Orders> getAllOrdersByPharmacy(Long pharmacyId) {
        ArrayList<Orders>  ret =  new  ArrayList<Orders>();

        for (Orders o : ordersRepository.findByPharmacyId(pharmacyId)  ) {

                ret.add(o);

        }
        return ret;
    }

    @Override
    public Orders deleteOrder(Long orderId) {

        //pregleda da li postoji data ponuda koja nije ni odbijena ni prihvacena
        ArrayList<OrderOffer> byOrderId = orderOfferRepository.findByOrderId(orderId);

        for(OrderOffer oo : byOrderId){
            if(oo.getStatus().equals(OrderOfferStatus.PENDING)){
               return null;
            }

        }

        //ako nema menja stanje narudzbenice u DELETED
        Orders order = ordersRepository.findById(orderId).orElseGet(null);
        order.setOrderStatus(OrderStatus.DELETED);
        ordersRepository.save(order);

        return order;
    }

    @Override
    public Orders createOrder(Orders newOrder) {
        Orders orders = new Orders();
        orders.setDeadline(newOrder.getDeadline().plusDays(1));
        orders.setPharmacyAdminId(newOrder.getPharmacyAdminId());
        orders.setPharmacyId(newOrder.getPharmacyId());
        orders.setPharmacyName(newOrder.getPharmacyName());
        orders.setOrderStatus(OrderStatus.ACTIVE);
        ordersRepository.save(orders);
        return orders;
    }

    @Override
    public Long createOrderItems(ArrayList<OrderItem> orderItems) {
        for(OrderItem iter : orderItems){
            OrderItem orderItem = new OrderItem();
            orderItem.setMedicineId(iter.getMedicineId());
            orderItem.setMedicineName(iter.getMedicineName());
            orderItem.setOrderId(iter.getOrderId());
            orderItem.setQuantity(iter.getQuantity());
            orderItemRepository.save(orderItem);
        }


        return orderItems.get(0).getOrderId();
    }
}
