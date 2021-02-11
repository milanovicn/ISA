import { OnInit } from "@angular/core";
import { Component } from "@angular/core";
import { Router } from "@angular/router";
import { User } from "app/ISA/shared/model/User";
import { DermatologistService } from "app/ISA/shared/service/dermatologist.service";
import { LoginService } from "app/ISA/shared/service/login.service";
import { PharmacistService } from "app/ISA/shared/service/pharmacist.service";

@Component({
    selector: 'patients',
    templateUrl: './patients.component.html',
})
export class PatientsFarmComponent implements OnInit {
    user: User;
    myPatients: User[] = [];
    searchParameters: User;

    ret: Object;

    constructor(private _router: Router, private loginService: LoginService, private dermatologistService: DermatologistService,
        private pharmacistService: PharmacistService) {
        this.user = new User();
        this.myPatients = [];
        this.searchParameters = new User();


    }

    getUser() {
        this.loginService.getLoggedInUser().subscribe({
            next: user => {
                this.user = user;
                this.getMyPatients();
            }
        });
    }

    getMyPatients() {

        this.pharmacistService.getMyPatients().subscribe({
            next: myPatients => {
                this.myPatients = myPatients;
            }
        });
    }

    ngOnInit(): void {
        this.getUser();


        console.log(this.user);
    }

    searchPatients() {
        console.log(this.searchParameters);
        let sp = new User();

        if (this.searchParameters.firstName == undefined) {
            sp.firstName = "all";
        } else {
            sp.firstName = this.searchParameters.firstName;
        }
        if (this.searchParameters.lastName == undefined) {;
            sp.lastName = "all";
        } else {
            sp.lastName = this.searchParameters.lastName;
        }


        console.log(this.searchParameters);
        console.log(sp);
    
        
        this.pharmacistService.searchPatients(sp.firstName, sp.lastName).subscribe({
          next: patient => {
            this.myPatients = patient;
          }
    
        });
        


    }
}
