package com.example.ISABackend.service;

import com.example.ISABackend.model.Rate;

import java.util.ArrayList;

public interface RateService {

    public Rate newRate(Rate newRate);
    public ArrayList<Rate> myRates(Long patientId);
    public Rate editRate(Rate updatedRate);

}
