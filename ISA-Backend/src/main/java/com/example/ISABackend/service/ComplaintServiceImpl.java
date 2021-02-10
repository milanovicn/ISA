package com.example.ISABackend.service;

import com.example.ISABackend.enums.ComplaintStatus;
import com.example.ISABackend.model.Complaint;
import com.example.ISABackend.repository.ComplaintRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ComplaintServiceImpl implements ComplaintService {

    @Autowired
    private ComplaintRepository complaintRepository;


    @Override
    public Complaint create(Complaint newComplaint) {
        Complaint complaint = new Complaint();
        complaint.setComplainAnswer("");
        complaint.setComplainText(newComplaint.getComplainText());
        complaint.setPatientEmail(newComplaint.getPatientEmail());
        complaint.setPatientId(newComplaint.getPatientId());
        complaint.setComplaintSubject(newComplaint.getComplaintSubject());
        complaint.setStatus(ComplaintStatus.CREATED);
        complaintRepository.save(complaint);

        return null;
    }

    @Override
    public Complaint answer(String answer, Long complaintId) {
        Complaint complaint = getById(complaintId);
        complaint.setComplainAnswer(answer);
        complaint.setStatus(ComplaintStatus.ANSWERED);
        complaintRepository.save(complaint);
        return complaint;
    }

    @Override
    public Complaint getById(Long complaintId) {
        return complaintRepository.findById(complaintId).orElseGet(null);
    }

    @Override
    public ArrayList<Complaint> getUnanswered() {
        ArrayList<Complaint> ret = new ArrayList<>();
        for(Complaint c : complaintRepository.findAll()){
            if(c.getStatus().equals(ComplaintStatus.CREATED)){
                ret.add(c);
            }
        }


        return ret;
    }
}
