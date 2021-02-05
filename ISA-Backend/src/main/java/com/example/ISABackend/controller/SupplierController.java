package com.example.ISABackend.controller;

import com.example.ISABackend.model.OrderItem;
import com.example.ISABackend.model.OrderOffer;
import com.example.ISABackend.model.Orders;
import com.example.ISABackend.model.Supplier;
import com.example.ISABackend.repository.SupplierRepository;
import com.example.ISABackend.service.OrdersService;
import com.example.ISABackend.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.core.Context;
import java.time.LocalDate;
import java.util.ArrayList;

@RestController
@RequestMapping("/api/supplier")
public class SupplierController {

    @Autowired
    SupplierService supplierService;

    @Autowired
    SupplierRepository supplierRepository;

    @Autowired
    OrdersService ordersService;


    @PutMapping(value = "/edit")
    public ResponseEntity updateUser(@RequestBody Supplier updatedSupplier, @Context HttpServletRequest request) {
        if (authorize(request) == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        Supplier supplier = supplierService.updateSupplier(updatedSupplier);
        request.getSession().setAttribute("supplier", supplier);
        return new ResponseEntity<Supplier>(supplier, HttpStatus.CREATED);
    }


    @GetMapping(value = "/activeOrders")
    public ResponseEntity getActiveOrders(@Context HttpServletRequest request) {
        if (authorize(request) == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        ArrayList<Orders> orders = ordersService.getActiveOrders();
        return new ResponseEntity<ArrayList<Orders>>(orders, HttpStatus.CREATED);
    }

    @GetMapping(value = "/orderItem/{orderId}")
    public ResponseEntity getItemsByOrderId(@PathVariable("orderId") Long orderId, @Context HttpServletRequest request) {
        if (authorize(request) == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        ArrayList<OrderItem> orders = ordersService.getItemsByOrderId(orderId);
        return new ResponseEntity<ArrayList<OrderItem>>(orders, HttpStatus.CREATED);
    }

    @PostMapping(value = "/offer/{orderId}/{price}")
    public ResponseEntity createOffer(@PathVariable("orderId") Long orderId,
                                      @PathVariable("price") Long price,
                                      @RequestBody LocalDate deliveryDate,
                                      @Context HttpServletRequest request) {
        if (authorize(request) == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        HttpSession session = request.getSession();
        Supplier s = (Supplier) session.getAttribute("supplier");
        OrderOffer offer = ordersService.createOffer(orderId, price, deliveryDate, s.getId());

        return new ResponseEntity<OrderOffer>(offer, HttpStatus.CREATED);
    }

    @GetMapping(value = "/offer/{orderId}")
    public ResponseEntity getOffer(@PathVariable("orderId") Long orderId,
                                   @Context HttpServletRequest request) {
        if (authorize(request) == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        HttpSession session = request.getSession();
        Supplier s = (Supplier) session.getAttribute("supplier");

        OrderOffer offer = ordersService.findOffer(orderId, s.getId());
        if (offer != null) {
            return new ResponseEntity<OrderOffer>(offer, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping(value = "/order/{orderId}")
    public ResponseEntity getOrder(@PathVariable("orderId") Long orderId, @Context HttpServletRequest request) {
        if (authorize(request) == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        Orders order = ordersService.getOrderById(orderId);
        return new ResponseEntity<Orders>(order, HttpStatus.CREATED);
    }


    @GetMapping(value = "/myOffers")
    public ResponseEntity myOffers(@Context HttpServletRequest request) {
        if (authorize(request) == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }


        HttpSession session = request.getSession();
        Supplier s = (Supplier) session.getAttribute("supplier");
        ArrayList<OrderOffer> myOrderOffers = ordersService.myOffers(s.getId());
        return new ResponseEntity<ArrayList<OrderOffer> >(myOrderOffers, HttpStatus.OK);
    }


    private Supplier authorize(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Supplier s = (Supplier) session.getAttribute("supplier");
        return s;
    }




}
