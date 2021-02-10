
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { DermatologistAppointmentDTO } from 'app/ISA/shared/model/DermatologistAppointmentDTO';
import { Medicine } from 'app/ISA/shared/model/Medicine';
import { MedicineReservation } from 'app/ISA/shared/model/MedicineReservation';
import { Pharmacy } from 'app/ISA/shared/model/Pharmacy';
import { User } from 'app/ISA/shared/model/User';
import { LoginService } from 'app/ISA/shared/service/login.service';
import { MedicineService } from 'app/ISA/shared/service/medicine.service';
import { UserService } from 'app/ISA/shared/service/user.service';


@Component({
    templateUrl: './medicines.component.html'
})


export class UserMedicinesComponent implements OnInit {
    allReservations: MedicineReservation[] = [];
    futureReservations: MedicineReservation[] = [];
    oldReservations: MedicineReservation[] = [];
    allMedicines: Medicine[] = [];
    availablePharmacies: Pharmacy[] = [];
    newReservation: MedicineReservation;
    user: User;
    ret: Object = new Object();

    constructor(private router: Router, private userService: UserService, private medicineService: MedicineService,
        private loginService: LoginService) {
        this.user = new User();
        this.newReservation = new MedicineReservation();
        this.newReservation.id=0;
    }

    ngOnInit(): void {
        this.getUser();
        this.getAllMedicines();
    }


    getUser() {
        this.loginService.getLoggedInUser().subscribe({
            next: user => {
                this.user = user;
                this.getMyReservations();
                this.newReservation.patientId = user.id;
                
            }
        });
    }

    refresh() {
        window.location.reload();
    }

    createReservation() {
        this.userService.makeReservation(this.newReservation).subscribe({
            next: newReservation => {
                this.newReservation = newReservation;
                console.log(this.ret);
                if(this.newReservation==null){
                    alert("You can't make this reservation!");
                } else {
                    alert("Reservation successfully made! Check your email for reservation identification number.");
                }
                this.refresh();
            }
        });

    }

    getMyReservations(){
        this.userService.getMyReservations(this.user.id).subscribe({
            next: allReservations => {
                this.allReservations = allReservations;
                for (let i = 0; i < this.allReservations.length; i++) {
                    if (this.allReservations[i].status == "RESERVED") {
                        this.futureReservations.push(this.allReservations[i]);
                    } else {
                        this.oldReservations.push(this.allReservations[i]);
                    }
                }    
            }
        });


    }

    getAvailablePharmacies() {
        this.userService.getAvailablePharmacies(this.newReservation.medicineId).subscribe({
            next: availablePharmacies => {
                this.availablePharmacies = availablePharmacies;
            }
        });
    }



    cancelReservation(reservationId: number) {

        this.userService.cancelMedicineReservation(reservationId).subscribe({
            next: reservationIdRet => {
                this.ret = reservationIdRet;
                if (this.ret == null) {
                    alert("Reservation can be canceled up to 24h beafore!");
                } else {
                    alert("Reservation successfully canceled!");
                }
                this.refresh();
            }
    
        });
    }
    
    getAllMedicines() {
    
        this.medicineService.getAllMedicines().subscribe({
            next: allMedicines => {
                this.allMedicines = allMedicines;
              
            }
        });
    }

    


}

