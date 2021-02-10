package com.example.ISABackend.service;

import com.example.ISABackend.model.Complaint;

import java.util.ArrayList;

public interface ComplaintService {
    public Complaint create(Complaint newComplaint);
    public Complaint answer(String answer, Long complaintId);
    public Complaint getById(Long complaintId);
    public ArrayList<Complaint> getUnanswered();


}
