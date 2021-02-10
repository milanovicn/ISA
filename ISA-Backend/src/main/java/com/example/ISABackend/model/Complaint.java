package com.example.ISABackend.model;

import com.example.ISABackend.enums.ComplaintStatus;

import javax.persistence.*;

@Entity
public class Complaint {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "patientId", nullable = false)
    private Long patientId;

    @Column(name = "complaintText", nullable = false)
    private String complaintText;

    @Column(name = "complaintSubject", nullable = false)
    private String complaintSubject;

    @Column(name = "complaintAnswer", nullable = false)
    private String complaintAnswer;

    @Column(name = "patientEmail", nullable = false)
    private String patientEmail;

    @Column(name = "status", nullable = false)
    private ComplaintStatus status;

    public Complaint() {
    }

    public Complaint(Long patientId, String complaintSubject, String complaintText, String complaintAnswer, String patientEmail, ComplaintStatus status) {
        this.patientId = patientId;
        this.complaintText = complaintText;
        this.complaintAnswer = complaintAnswer;
        this.patientEmail = patientEmail;
        this.status = status;
        this.complaintSubject = complaintSubject;
    }

    public String getComplaintText() {
        return complaintText;
    }

    public void setComplaintText(String complaintText) {
        this.complaintText = complaintText;
    }

    public String getComplaintSubject() {
        return complaintSubject;
    }

    public void setComplaintSubject(String complaintSubject) {
        this.complaintSubject = complaintSubject;
    }

    public String getComplaintAnswer() {
        return complaintAnswer;
    }

    public void setComplaintAnswer(String complaintAnswer) {
        this.complaintAnswer = complaintAnswer;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPatientId() {
        return patientId;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }

    public String getComplainText() {
        return complaintText;
    }

    public void setComplainText(String complainText) {
        this.complaintText = complainText;
    }

    public String getComplainAnswer() {
        return complaintAnswer;
    }

    public void setComplainAnswer(String complainAnswer) {
        this.complaintAnswer = complainAnswer;
    }

    public String getPatientEmail() {
        return patientEmail;
    }

    public void setPatientEmail(String patientEmail) {
        this.patientEmail = patientEmail;
    }

    public ComplaintStatus getStatus() {
        return status;
    }

    public void setStatus(ComplaintStatus status) {
        this.status = status;
    }
}
