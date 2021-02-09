import { Component, OnInit } from "@angular/core";
import { ActivatedRoute, Router } from "@angular/router";
import { LoginService } from "app/ISA/shared/service/login.service";
import { User } from "app/ISA/shared/model/User";
import { HttpClient } from "@angular/common/http";
import { PharmacyService } from "app/ISA/shared/service/pharmacy.service";
import { Pharmacy } from "app/ISA/shared/model/Pharmacy";
import { DermatologistAppointmentDTO } from "app/ISA/shared/model/DermatologistAppointmentDTO";
import { UserService } from "app/ISA/shared/service/user.service";
import { DermatologistService } from "app/ISA/shared/service/dermatologist.service";

@Component({
    templateUrl: './dermatologist-appointment.component.html'
})

export class DermatologistAppointmentComponent implements OnInit {
    ret: Object;
    user: User;
    request: Request;
    id: number;
    appointment: DermatologistAppointmentDTO
    pharmacy: Pharmacy;
    errorMessage = '';
    availableDermatologistAppointments: DermatologistAppointmentDTO[] = [];

    constructor(private httpClient: HttpClient, private route: ActivatedRoute,
        private router: Router, private loginService: LoginService, private pharmacyService: PharmacyService,
        private userService: UserService, private dermatologistService: DermatologistService) {
        this.user = new User();
        this.pharmacy = new Pharmacy();
        this.appointment = new DermatologistAppointmentDTO();

    }

    ngOnInit(): void {
        this.getUser();
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
                this.getAvailableAppointments();
            }

        }

        );
    }

    getUser() {
        this.loginService.getLoggedInUser().subscribe({
            next: user => {
                this.user = user;
            }
        });
    }

    getAvailableAppointments() {
        this.dermatologistService.getAvailableAppointments().subscribe({
            next: appointments => {
                this.availableDermatologistAppointments = appointments;
            }

        });

    }

    makeDermatologistAppointment(appointmentId) {
        console.log(appointmentId);
        this.dermatologistService.appointmentReserveForUser(appointmentId, this.appointment.patientId ).subscribe({
            next: appointmentIdRet => {
                this.ret = appointmentIdRet;
                alert("Uspesno ste napravili rezervaciju!");
            }

        });

    }

}