import { HttpClient } from "@angular/common/http";
import { Component, OnInit } from "@angular/core";
import { ActivatedRoute, Router } from "@angular/router";
import { DermatologistAppointmentDTO } from "app/ISA/shared/model/DermatologistAppointmentDTO";
import { Medicine } from "app/ISA/shared/model/Medicine";
import { DermatologistService } from "app/ISA/shared/service/dermatologist.service";
import { MedicineService } from "app/ISA/shared/service/medicine.service";

@Component({
    selector: 'dermatologist-report',
    templateUrl: './dermatologist-report.component.html'
})


export class DermatologistReportComponent implements OnInit {
    availableMedicine : Medicine[] = [];
    id: number;
    appointment: DermatologistAppointmentDTO;
    reportMedicineId: number = 0;
    reportDuration: number= 0;
    available: boolean = false;
    reportText: String = "";
    ret: Object;

 
    constructor( private router: Router, private dermatologistService: DermatologistService,
                 private httpClient: HttpClient, private route: ActivatedRoute, 
                 private medicineService: MedicineService) { 
     this.ret = new Object();
    } 
  
   

    ngOnInit(): void {
        //this.getUser();
        const param = this.route.snapshot.paramMap.get('id');
        if (param) {
            this.id = +param;
            this.getProduct(this.id);

        }

    }

    getProduct(id: number) {
        this.dermatologistService.getAppointmentId(id).subscribe({
            next: appointment => {
                this.appointment = appointment;
                this.getAvailableMedicine();
            }

        }

        );
    }

    getAvailableMedicine(){
        this.dermatologistService.getMedicineForUser(this.appointment.patientId).subscribe({
            next: appointment => {
                this.availableMedicine = appointment;
            }

        }

        );
    }

    patientDidntShowUp(){

        this.dermatologistService.didntShowUp(this.appointment.appointmentId).subscribe({
            next: appointment => {
                this.ret = appointment;
            }
        }
        );

    }

    createReport(){
        this.dermatologistService.createReport(this.appointment.appointmentId, this.reportText,
            this.appointment.dermatologistId, this.reportMedicineId, this.reportDuration).subscribe({
            next: appointment => {
                this.ret = appointment;
            }
        }
        );


    }

    checkAvailability(){
        this.dermatologistService.checkAvailability(this.appointment.pharmacyId, this.reportMedicineId).subscribe({
            next: appointment => {
                this.available = appointment;
            }
        }
        );

    }

    sendInquiry(){

        this.dermatologistService.sendInquiry(this.reportMedicineId, this.appointment.pharmacyId).subscribe({
            next: appointment => {
                this.ret = appointment;
            }
        }
        );

    }

}