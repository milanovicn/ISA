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
    isSubscribed:boolean=false;

    constructor(private httpClient: HttpClient, private route: ActivatedRoute,
        private router: Router, private loginService: LoginService, private pharmacyService: PharmacyService,
        private userService: UserService) {
        this.user = new User();
        this.pharmacy = new Pharmacy();

    }

    ngOnInit(): void {
        
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
                this.getUser();
            }

        }

        );
    }

    getUser() {
        this.loginService.getLoggedInUser().subscribe({
            next: user => {
                this.user = user;
                this.getSubscription();
            }
        });
    }

    getSubscription() {
        this.userService.isSubscribed(this.user.id, this.pharmacy.id).subscribe({
            next: isSubscribed => {
                this.isSubscribed = isSubscribed;
               
            }
        });
    }

    subscribeToActions(){
        this.userService.subscribe(this.user.id, this.pharmacy.id).subscribe({
            next: isSubscribed => {
                this.isSubscribed = isSubscribed;
            
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
                this.refresh();
                alert("Reservation made");

                
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
                this.refresh();
                alert("Appointment made");
            }

        });

    }



}