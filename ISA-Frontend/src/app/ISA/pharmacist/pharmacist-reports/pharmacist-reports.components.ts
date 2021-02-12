import { Component, OnInit } from "@angular/core";
import { Router } from "@angular/router";
import { PharmacistAppointmentDTO } from "app/ISA/shared/model/PharmacistAppointmentDTO";
import { PharmacistService } from "app/ISA/shared/service/pharmacist.service";

@Component({
    selector: 'pharmacist-reports',
    templateUrl: './pharmacist-reports.component.html'
})


export class PharmacistReportsComponent implements OnInit {
    allAppointments : PharmacistAppointmentDTO[] = [];
 

    constructor( private router: Router, private pharmacistService: PharmacistService) { 
     
    } 
  
   

    ngOnInit(): void {
        this.pharmacistService.getReservedAppointments().subscribe({
            next: appointments => {
                this.allAppointments = appointments;
            }

        });

    }
}