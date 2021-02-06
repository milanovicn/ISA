
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { DermatologistAppointmentDTO } from 'app/ISA/shared/model/DermatologistAppointmentDTO';
import { User } from 'app/ISA/shared/model/User';
import { LoginService } from 'app/ISA/shared/service/login.service';
import { UserService } from 'app/ISA/shared/service/user.service';


@Component({
    templateUrl: './pharmacist-appointment.component.html'
})


export class PharmacistAppointmentsComponent implements OnInit {
    allAppointments: DermatologistAppointmentDTO[] = [];
    oldAppontments: DermatologistAppointmentDTO[] = [];
    futureAppointments: DermatologistAppointmentDTO[] = [];
    user: User;
    ret: Object = new Object();

    constructor(private router: Router, private userService: UserService, private loginService: LoginService) {
        this.user = new User();

    }

    ngOnInit(): void {
        this.getUser();
    }

    getMyPharmacistAppointments() {

        this.userService.getMyPharmacistAppointments(this.user.id).subscribe({
            next: allAppointments => {
                this.allAppointments = allAppointments;

                for (let i = 0; i < this.allAppointments.length; i++) {
                    if (this.allAppointments[i].appointmentStatus == "RESERVED") {
                        this.futureAppointments.push(this.allAppointments[i]);
                    } else {
                        this.oldAppontments.push(this.allAppointments[i]);
                    }
                }



            }

        });


    }

    getUser() {
        this.loginService.getLoggedInUser().subscribe({
            next: user => {
                this.user = user;
                this.getMyPharmacistAppointments();
            }
        });
    }


    cancelReservation(reservationId: number) {

        this.userService.cancelPharmacistAppointment(reservationId).subscribe({
            next: appointmentIdRet => {
                this.ret = appointmentIdRet;
                console.log(this.ret);
                if(this.ret==null){
                    alert("Reservation can be canceled up to 24h beafore!");
                } else {
                    alert("Reservation successfully canceled!");
                }
                this.refresh();
            }

        });
    }

    refresh() {
        window.location.reload();
    }


    sort(sortType: string) {
        console.log(sortType);
        this.userService.sortAppointments(this.oldAppontments, sortType).subscribe({
            next: oldAppontments => {
                this.oldAppontments = oldAppontments;
            }

        });
    }


}