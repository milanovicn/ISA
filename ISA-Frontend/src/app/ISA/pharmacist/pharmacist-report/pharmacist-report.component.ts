import { HttpClient } from "@angular/common/http";
import { Component, OnInit } from "@angular/core";
import { ActivatedRoute, Router } from "@angular/router";
import { DermatologistAppointmentDTO } from "app/ISA/shared/model/DermatologistAppointmentDTO";
import { Medicine } from "app/ISA/shared/model/Medicine";
import { PharmacistAppointmentDTO } from "app/ISA/shared/model/PharmacistAppointmentDTO";
import { DermatologistService } from "app/ISA/shared/service/dermatologist.service";
import { MedicineService } from "app/ISA/shared/service/medicine.service";
import { PharmacistService } from "app/ISA/shared/service/pharmacist.service";

@Component({
    selector: 'pharmacist-report',
    templateUrl: './pharmacist-report.component.html'
})


export class PharmacistReportComponent implements OnInit {
    availableMedicine : Medicine[] = [];
    id: number;
    appointment: PharmacistAppointmentDTO;
    reportMedicineId: number = 0;
    reportDuration: number= 0;
    available: boolean = false;
    reportText: String = "";
    ret: Object;

 
    constructor( private router: Router, private dermatologistService: DermatologistService,
                 private httpClient: HttpClient, private route: ActivatedRoute, 
                 private medicineService: MedicineService, private pharmacistService: PharmacistService) { 
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
        this.pharmacistService.getAppointmentId(id).subscribe({
            next: appointment => {
                this.appointment = appointment;
                this.getAvailableMedicine();
            }

        }

        );
    }

    getAvailableMedicine(){
        this.pharmacistService.getMedicineForUser(this.appointment.patientId).subscribe({
            next: appointment => {
                this.availableMedicine = appointment;
            }

        }

        );
    }

    patientDidntShowUp(){

        this.pharmacistService.didntShowUp(this.appointment.appointmentId).subscribe({
            next: appointment => {
                this.ret = appointment;
            }
        }
        );

    }

    createReport(){
        this.pharmacistService.createReport(this.appointment.appointmentId, this.reportText,
            this.appointment.pharmacistId, this.reportMedicineId, this.reportDuration).subscribe({
            next: appointment => {
                this.ret = appointment;
            }
        }
        );


    }

    checkAvailability(){
        this.pharmacistService.checkAvailability(this.appointment.pharmacyId, this.reportMedicineId).subscribe({
            next: appointment => {
                this.available = appointment;
            }
        }
        );

    }

    sendInquiry(){

        this.pharmacistService.sendInquiry(this.reportMedicineId, this.appointment.pharmacyId).subscribe({
            next: appointment => {
                this.ret = appointment;
            }
        }
        );

    }

}