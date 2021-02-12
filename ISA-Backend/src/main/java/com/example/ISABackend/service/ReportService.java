package com.example.ISABackend.service;

import com.example.ISABackend.model.Report;

public interface ReportService {
    public Report addReport(Report report, Long appointmentId);
   public Report createReport(Long appointmentId, Long dermatologistId, Long reportMedicineId, Long reportDuration, String reportText);
}
