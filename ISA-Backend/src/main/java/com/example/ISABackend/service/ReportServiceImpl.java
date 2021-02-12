package com.example.ISABackend.service;

import com.example.ISABackend.model.Report;
import com.example.ISABackend.repository.ReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReportServiceImpl implements ReportService{

    @Autowired
    ReportRepository reportRepository;

    @Override
    public Report addReport(Report report, Long appointmentId) {
        return null;
    }

    @Override
    public Report createReport(Long appointmentId, Long dermatologistId, Long reportMedicineId,
                               Long reportDuration, String reportText) {


        Report report = new Report();
        report.setAppointmentId(appointmentId);
        report.setDoctorId(dermatologistId);
        report.setMedicineId(reportMedicineId);
        report.setTherapyDuration(reportDuration);
        report.setReportText(reportText);
        report.setMedicineName("ok");
        report.setPatientId(101L);
        report.setPatientName("ok");


        reportRepository.save(report);
        return report;
    }
}
