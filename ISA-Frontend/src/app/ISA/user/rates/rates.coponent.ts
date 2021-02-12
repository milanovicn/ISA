import { Component, OnInit } from '@angular/core';
import { ɵangular_packages_platform_browser_platform_browser_i } from '@angular/platform-browser';
import { Router } from '@angular/router';
import { Complaint } from 'app/ISA/shared/model/Complaint';
import { Dermatologist } from 'app/ISA/shared/model/Dermatologist';
import { DermatologistAppointmentDTO } from 'app/ISA/shared/model/DermatologistAppointmentDTO';
import { MedicineReservation } from 'app/ISA/shared/model/MedicineReservation';
import { Pharmacist } from 'app/ISA/shared/model/Pharmacist';
import { Pharmacy } from 'app/ISA/shared/model/Pharmacy';
import { Rate } from 'app/ISA/shared/model/Rate';
import { User } from 'app/ISA/shared/model/User';
import { LoginService } from 'app/ISA/shared/service/login.service';
import { UserService } from 'app/ISA/shared/service/user.service';


@Component({
    templateUrl: './rates.component.html'
})


export class RatesComponent implements OnInit {
    unratedPharmacists: DermatologistAppointmentDTO[] = [];
    unratedDermatologists:DermatologistAppointmentDTO[] = [];
    unratedMedicines:MedicineReservation[] = [];
    unratedPharmacies:MedicineReservation[] = [];
    myRates:Rate[] = [];
    user: User;
    ret: Object = new Object();
    pharmacyRateValue = 0;
    pharmacyToRateId = 0;
    pharmacistRateValue = 0;
    pharmacistToRateId = 0;
    dermatologistRateValue = 0;
    dermatologistToRateId = 0;
    medicineRateValue = 0;
    medicineToRateId = 0;

    constructor(private router: Router, private userService: UserService, private loginService: LoginService) {
        this.user = new User();
        
    }

    ngOnInit(): void {
        this.getUser();
    }


    getUser() {
        this.loginService.getLoggedInUser().subscribe({
            next: user => {
                this.user = user;
                this.fillData();
            }
        });
    }

    fillData() {
        this.userService.getUnratedPharmacists(this.user.id).subscribe({
            next: unratedPharmacists => {
                this.unratedPharmacists = unratedPharmacists;  
              
            }
        });

        this.userService.getUnratedDermatologists(this.user.id).subscribe({
            next: unratedDermatologists => {
                this.unratedDermatologists = unratedDermatologists;  
              
            }
        });

        this.userService.getUnratedMedicines(this.user.id).subscribe({
            next: unratedMedicines => {
                this.unratedMedicines = unratedMedicines;  
              
            }
        }); 

        this.userService.getUnratedPharmacies(this.user.id).subscribe({
            next: unratedPharmacies => {
                this.unratedPharmacies = unratedPharmacies;  
              
            }
        });


        this.userService.getMyRates(this.user.id).subscribe({
            next: myRates => {
                this.myRates = myRates;  
              
            }
        });


    }
    

    ratePharmacy() {
        let newRate = new Rate();

        newRate.ratedSubject="";
        newRate.patientId = this.user.id;
        newRate.ratedId = 0;
        newRate.ratedType = "PHARMACY";
        newRate.rate = this.pharmacyRateValue;
        newRate.reservationId =  this.pharmacyToRateId;
        console.log(newRate);
        this.userService.rate(newRate).subscribe({
            next: ret => {
                this.ret = ret;
                console.log(this.ret);
                alert("Rate successful!");
               this.refresh();
            }

        });
    }

    rateMedicine() {
        let newRate = new Rate();

        newRate.ratedSubject="";
        newRate.patientId = this.user.id;
        newRate.ratedId = 0;
        newRate.ratedType = "MEDICINE";
        newRate.rate = this.medicineRateValue;
        newRate.reservationId = this.medicineToRateId;

        console.log(newRate);
        this.userService.rate(newRate).subscribe({
            next: ret => {
                this.ret = ret;
                console.log(this.ret);
                this.refresh();
                alert("Rate successful!");
               this.refresh();
            }

        });
    }

    ratePharmacist() {
        let newRate = new Rate();

        newRate.ratedSubject="";
        newRate.patientId = this.user.id;
        newRate.ratedId = 0;
        newRate.ratedType = "PHARMACIST";
        newRate.rate = this.pharmacistRateValue;
        newRate.reservationId = this.pharmacistToRateId;

        console.log(newRate);
        this.userService.rate(newRate).subscribe({
            next: ret => {
                this.ret = ret;
                console.log(this.ret);
                this.refresh();
                alert("Rate successful!");
                this.refresh();
            }

        });
    }

    rateDermatologist() {
        let newRate = new Rate();

        newRate.ratedSubject="";
        newRate.patientId = this.user.id;
        newRate.ratedId = 0;
        newRate.ratedType = "DERMATOLOGIST";
        newRate.rate = this.dermatologistRateValue;
        newRate.reservationId = this.dermatologistToRateId;

        console.log(newRate);
        this.userService.rate(newRate).subscribe({
            next: ret => {
                this.ret = ret;
                console.log(this.ret);
                alert("Rate successful!");
                this.refresh();
            }

        });
    }

    editRate(updatedRate:Rate){
        console.log(updatedRate);

        let toSend =updatedRate[0];
        this.userService.editRate(toSend).subscribe({
            next: ret => {
                this.ret = ret;
                console.log(this.ret);
                alert("Rate edit successful!");
               this.refresh();
            }

        });

    }

    refresh() {
        window.location.reload();
    }



}