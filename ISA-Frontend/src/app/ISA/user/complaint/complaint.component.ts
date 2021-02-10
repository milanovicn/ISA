import { Component, OnInit } from '@angular/core';
import { ɵangular_packages_platform_browser_platform_browser_i } from '@angular/platform-browser';
import { Router } from '@angular/router';
import { Complaint } from 'app/ISA/shared/model/Complaint';
import { Dermatologist } from 'app/ISA/shared/model/Dermatologist';
import { Pharmacist } from 'app/ISA/shared/model/Pharmacist';
import { Pharmacy } from 'app/ISA/shared/model/Pharmacy';
import { User } from 'app/ISA/shared/model/User';
import { LoginService } from 'app/ISA/shared/service/login.service';
import { UserService } from 'app/ISA/shared/service/user.service';


@Component({
    templateUrl: './complaint.component.html'
})


export class ComplaintComponent implements OnInit {
    myPharmacies: Pharmacy[] = [];
    myDermatologists:Dermatologist[] = [];
    myPharmacists:Pharmacist[] = [];
    user: User;
    ret: Object = new Object();
    newComplaint: Complaint;
    subjects : string[] = [];

    constructor(private router: Router, private userService: UserService, private loginService: LoginService) {
        this.user = new User();
        this.newComplaint = new Complaint();
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
    }

    refresh() {
        window.location.reload();
    }



}