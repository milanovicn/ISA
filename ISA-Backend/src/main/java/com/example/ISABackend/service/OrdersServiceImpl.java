package com.example.ISABackend.service;

import com.example.ISABackend.enums.OrderOfferStatus;
import com.example.ISABackend.enums.OrderStatus;
import com.example.ISABackend.model.OrderItem;
import com.example.ISABackend.model.OrderOffer;
import com.example.ISABackend.model.Orders;
import com.example.ISABackend.repository.OrderItemRepository;
import com.example.ISABackend.repository.OrderOfferRepository;
import com.example.ISABackend.repository.OrdersRepository;
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


}
