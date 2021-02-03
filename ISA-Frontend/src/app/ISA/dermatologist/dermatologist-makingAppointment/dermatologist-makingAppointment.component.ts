import { Component, OnInit } from "@angular/core";
import { ActivatedRoute, Router } from "@angular/router";
import { LoginService } from "app/ISA/shared/service/login.service";
import { User } from "app/ISA/shared/model/User";
import { HttpClient } from "@angular/common/http";
import { DermatologistAppointmentDTO } from "app/ISA/shared/model/DermatologistAppointmentDTO";
import { DermatologistService } from "app/ISA/shared/service/dermatologist.service";


@Component({
    templateUrl: './dermatologist-makingAppointment.component.html'
})

export class DermatologistMakingAppointmentComponent implements OnInit {
    ret: Object;
    request: Request;
    errorMessage = '';

    user: User; //moj dermatolog
    availableDermatologistAppointments: DermatologistAppointmentDTO[] = [];

    constructor(private httpClient: HttpClient, private route: ActivatedRoute,
        private router: Router, private loginService: LoginService, private dermatologistService: DermatologistService) {

        this.user = new User();

    }

    ngOnInit(): void {
        this.getUser();
        this.getAllDermatologistAppointments;
    }

    getUser() {
        this.loginService.getLoggedInUser().subscribe({
            next: user => {
                this.user = user;
            }
        });
    }

    
    getAllDermatologistAppointments() {
        this.dermatologistService.availableDermatologistAppointments(this.user.id).subscribe({
            next: appointments => {
                this.availableDermatologistAppointments = appointments;
            }

        });

    }

    /*
    makeDermatologistAppointment(appointmentId) {
        console.log(appointmentId);
        this.dermatologistService.makeDermatologistAppointment(this.user, appointmentId).subscribe({
            next: appointmentIdRet => {
                this.ret = appointmentIdRet;
                alert("Uspesno ste napravili rezervaciju!");
            }

        });
        

    }
    */

}