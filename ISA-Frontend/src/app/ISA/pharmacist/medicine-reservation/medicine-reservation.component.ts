import { Component, OnInit } from '@angular/core';
import { ɵangular_packages_platform_browser_platform_browser_i } from '@angular/platform-browser';
import { Router } from '@angular/router';
import { Complaint } from 'app/ISA/shared/model/Complaint';
import { Dermatologist } from 'app/ISA/shared/model/Dermatologist';
import { MedicineReservation } from 'app/ISA/shared/model/MedicineReservation';
import { Pharmacist } from 'app/ISA/shared/model/Pharmacist';
import { Pharmacy } from 'app/ISA/shared/model/Pharmacy';
import { User } from 'app/ISA/shared/model/User';
import { LoginService } from 'app/ISA/shared/service/login.service';
import { PharmacistService } from 'app/ISA/shared/service/pharmacist.service';
import { UserService } from 'app/ISA/shared/service/user.service';


@Component({
    templateUrl: './medicine-reservation.component.html'
})


export class MedicineReservationComponent implements OnInit {
    ret: Object = new Object();
    medicineReservation: MedicineReservation;
    reservationCode:string = "";
    approved:boolean = false;

    constructor(private router: Router, private pharmacistService: PharmacistService) {
     
        this.medicineReservation = new MedicineReservation();
    }

    ngOnInit(): void {
    }

    refresh() {
        window.location.reload();
    }


    checkCode(){
        this.pharmacistService.checkMedicineReservationCode(this.reservationCode).subscribe({
            next: medR => {
                this.medicineReservation = medR;
                if(medR == null){
                    alert("Incorrect code!");
                } else {
                    this.medicineReservation = medR;
                    this.approved = true;
                    alert("Code approved!");
                }

            }
        });

    }

    
    issueMedicine(){
        this.pharmacistService.issueMedicineReservation(this.medicineReservation.id).subscribe({
            next: medR => {
                this.medicineReservation = medR;
                if(medR == null){
                    alert("Unsuccessful!");
                } else {
                    this.medicineReservation = medR;
                    alert("Successful!");
                    this. refresh();
                }

            }
        });
    }

    /* getUser() {
        this.loginService.getLoggedInUser().subscribe({
            next: user => {
                this.user = user;
                this.fillData();
            }
        });
    }

    fillData() {
        this.userService.getMyPharmacies(this.user.id).subscribe({
            next: myPharmacies => {
                this.myPharmacies = myPharmacies;  
                for(let i = 0; i < this.myPharmacies.length; i++ ){
                    this.subjects.push(this.myPharmacies.length[i]);
                }
            }
        });

        this.userService.getMyDermatologists(this.user.id).subscribe({
            next: myDermatologists => {
                this.myDermatologists = myDermatologists;  
                for(let i = 0; i < this.myDermatologists.length; i++ ){
                    this.subjects.push(this.myDermatologists.length[i]);
                }

            }
        });

        this.userService.getMyPharmacists(this.user.id).subscribe({
            next: myPharmacists => {
                this.myPharmacists = myPharmacists;  
                for(let i = 0; i < this.myPharmacists.length; i++ ){
                    this.subjects.push(this.myPharmacists.length[i]);
                }
            }
        });      

        console.log(this.subjects);

    }
    

    makeComplaint() {
        this.newComplaint.patientEmail = this.user.email;
        this.newComplaint.patientId = this.user.id;
        this.userService.makeComplaint(this.newComplaint).subscribe({
            next: ret => {
                this.ret = ret;
                console.log(this.ret);
                alert("Complaint successfully sent!");
                this.refresh();
            }

        });
    } */

  



}