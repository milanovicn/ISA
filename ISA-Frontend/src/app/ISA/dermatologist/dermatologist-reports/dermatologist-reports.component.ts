import { Component, OnInit } from "@angular/core";
import { Router } from "@angular/router";
import { DermatologistAppointmentDTO } from "app/ISA/shared/model/DermatologistAppointmentDTO";
import { DermatologistService } from "app/ISA/shared/service/dermatologist.service";

@Component({
    selector: 'dermatologist-reports',
    templateUrl: './dermatologist-reports.component.html'
})


export class DermatologistReportsComponent implements OnInit {
    allAppointments : DermatologistAppointmentDTO[] = [];
 

    constructor( private router: Router, private dermatologistService: DermatologistService) { 
     
    } 
  
   

    ngOnInit(): void {
        this.dermatologistService.getReservedAppointments().subscribe({
            next: appointments => {
                this.allAppointments = appointments;
            }

        });

    }
}