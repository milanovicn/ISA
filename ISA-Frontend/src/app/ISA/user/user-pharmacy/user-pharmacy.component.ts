import { Component, OnInit } from "@angular/core";
import { ActivatedRoute, Router } from "@angular/router";
import { LoginService } from "app/ISA/shared/service/login.service";
import { User } from "app/ISA/shared/model/User";
import { HttpClient } from "@angular/common/http";
import { PharmacyService } from "app/ISA/shared/service/pharmacy.service";
import { Pharmacy } from "app/ISA/shared/model/Pharmacy";
import { DermatologistAppointmentDTO } from "app/ISA/shared/model/DermatologistAppointmentDTO";
import { UserService } from "app/ISA/shared/service/user.service";

@Component({
    templateUrl: './user-pharmacy.component.html'
})

export class UserPharmacyComponent implements OnInit {
    ret: Object;
    user: User;
    request: Request;
    id: number;
    pharmacy: Pharmacy;
    errorMessage = '';
    availableDermatologistAppointments: DermatologistAppointmentDTO[] = [];
    availablePharmacistAppointments: DermatologistAppointmentDTO[] = [];


    constructor(private httpClient: HttpClient, private route: ActivatedRoute,
        private router: Router, private loginService: LoginService, private pharmacyService: PharmacyService,
        private userService: UserService) {
        this.user = new User();
        this.pharmacy = new Pharmacy();

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
        this.pharmacyService.getPharmacy(id).subscribe({
            next: pharmacy => {
                this.pharmacy = pharmacy;
                this.getAllDermatologistAppointments();
                this.getAllPharmacistAppointments();
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

    getAllDermatologistAppointments() {
        this.pharmacyService.availableDermatologistAppointments(this.pharmacy.id).subscribe({
            next: appointments => {
                this.availableDermatologistAppointments = appointments;
            }

        });

    }

    getAllPharmacistAppointments() {
        this.pharmacyService.availablePharmacistAppointments(this.pharmacy.id).subscribe({
            next: appointments => {
                this.availablePharmacistAppointments = appointments;
            }

        });

    }

    makeDermatologistAppointment(appointmentId) {
        console.log(appointmentId);
        this.userService.makeDermatologistAppointment(this.user, appointmentId).subscribe({
            next: appointmentIdRet => {
                this.ret = appointmentIdRet;
                alert("Uspesno ste napravili rezervaciju!");

                this.refresh();
            }

        });

    }

    refresh(){
        window.location.reload();
    }

    makePharmacistCounseling(councelingId) {
        console.log(councelingId);
        this.userService.makePharmacistAppointment(this.user, councelingId).subscribe({
            next: appointmentIdRet => {
                this.ret = appointmentIdRet;
                alert("Uspesno ste napravili rezervaciju!");
            }

        });

    }



}