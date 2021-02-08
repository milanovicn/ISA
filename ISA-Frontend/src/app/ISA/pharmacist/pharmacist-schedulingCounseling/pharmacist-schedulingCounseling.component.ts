import { Component, OnInit } from "@angular/core";
import { ActivatedRoute, Router } from "@angular/router";
import { LoginService } from "app/ISA/shared/service/login.service";
import { User } from "app/ISA/shared/model/User";
import { HttpClient } from "@angular/common/http";
import { PharmacistService } from "app/ISA/shared/service/pharmacist.service";
import { Pharmacist } from "app/ISA/shared/model/Pharmacist";


@Component({
    templateUrl: './pharmacist-schedulingCounseling.component.html'
})

export class PharmacistSchedulingCounseling implements OnInit {
    ret: Object;
    request: Request;
    errorMessage = '';

    user: User; //moj farmaceut
    availablePharmacistCounseling: Pharmacist[] = []; /////////////////

    constructor(private httpClient: HttpClient, private route: ActivatedRoute,
        private router: Router, private loginService: LoginService, private pharmacistService: PharmacistService) {

        this.user = new User();

    }

    ngOnInit(): void {
        this.getUser();
        this.getAllPharmacistCounseling;
    }

    getUser() {
        this.loginService.getLoggedInUser().subscribe({
            next: user => {
                this.user = user;
            }
        });
    }

    
    getAllPharmacistCounseling() {
        this.pharmacistService.availablePharmacistCounseling(this.user.id).subscribe({
            next: counseling => {
                this.availablePharmacistCounseling = counseling;
            }

        });

    }

    /*
    makePharmacistCounseling(appointmentId) {
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