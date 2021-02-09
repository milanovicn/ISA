package com.example.ISABackend.model;

import javax.persistence.*;

@Entity
public class PatientPenalty {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "patientId", nullable = false)
    private Long patientId;

    @Column(name = "penaltyNumber", nullable = false)
    private Long penaltyNumber;

    public PatientPenalty() {
    }

    public PatientPenalty(Long patientId, Long penaltyNumber) {
        this.patientId = patientId;
        this.penaltyNumber = penaltyNumber;
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

    public Long getPenaltyNumber() {
        return penaltyNumber;
    }

    public void setPenaltyNumber(Long penaltyNumber) {
        this.penaltyNumber = penaltyNumber;
    }
}
