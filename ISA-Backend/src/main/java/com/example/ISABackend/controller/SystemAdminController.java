package com.example.ISABackend.controller;


import com.example.ISABackend.repository.SupplierRepository;
import com.example.ISABackend.repository.SystemAdminRepository;
import com.example.ISABackend.service.SupplierService;
import com.example.ISABackend.service.SystemAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/system-admin")
public class SystemAdminController {

    @Autowired
    SystemAdminService systemAdminService;

    @Autowired
    SystemAdminRepository systemAdminRepository;


}
