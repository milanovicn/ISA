package com.example.ISABackend.model;

import javax.persistence.*;

@Entity
public class Report {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "appointmentId", nullable = false)
    private Long appointmentId;

    @Column(name = "doctorId")
    private Long doctorId;

    @Column(name = "medicineId")
    private Long medicineId;

    @Column(name = "medicineName")
    private String medicineName;

    @Column(name = "patientId")
    private Long patientId;

    @Column(name = "patientName")
    private String patientName;

    @Column(name = "therapyDuration")
    private Long therapyDuration;

    @Column(name = "reportText")
    private String reportText;


    public Report(Long appointmentId, Long doctorId, Long medicineId, String medicineName, Long patientId, String patientName,
                  Long therapyDuration, String reportText) {

        this.appointmentId = appointmentId;
        this.doctorId = doctorId;
        this.medicineId = medicineId;
        this.medicineName = medicineName;
        this.patientId = patientId;
        this.patientName = patientName;
        this.therapyDuration = therapyDuration;
        this.reportText = reportText;
    }

    public Report() {

    }

    public Long getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(Long appointmentId) {
        this.appointmentId = appointmentId;
    }

    public Long getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Long doctorId) {
        this.doctorId = doctorId;
    }

    public Long getMedicineId() {
        return medicineId;
    }

    public void setMedicineId(Long medicineId) {
        this.medicineId = medicineId;
    }

    public Long getTherapyDuration() {
        return therapyDuration;
    }

    public void setTherapyDuration(Long therapyDuration) {
        this.therapyDuration = therapyDuration;
    }

    public String getReportText() {
        return reportText;
    }

    public void setReportText(String reportText) {
        this.reportText = reportText;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMedicineName() {
        return medicineName;
    }

    public void setMedicineName(String medicineName) {
        this.medicineName = medicineName;
    }

    public Long getPatientId() {
        return patientId;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }
}
