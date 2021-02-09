import { OnInit } from "@angular/core";
import { Component } from "@angular/core";
import { Router } from "@angular/router";
import { User } from "app/ISA/shared/model/User";
import { DermatologistService } from "app/ISA/shared/service/dermatologist.service";
import { LoginService } from "app/ISA/shared/service/login.service";

@Component({
    selector: 'patients',
    templateUrl: './patients.component.html',
})
export class PatientsDermComponent implements OnInit {
    user: User;
    myPatients: User[] = [];
    searchParameters: User;

    ret: Object;

    constructor(private _router: Router, private loginService: LoginService, private dermatologistService: DermatologistService) {
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

        this.dermatologistService.getMyPatients().subscribe({
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
            console.log("uso1");
            sp.firstName = "all";
        } else {
            console.log("uso2");
            sp.firstName = this.searchParameters.firstName;
        }
        if (this.searchParameters.lastName == undefined) {
            console.log("uso3");
            sp.lastName = "all";
        } else {
            console.log("uso4");
            sp.lastName = this.searchParameters.lastName;
        }


        console.log(this.searchParameters);
        console.log(sp);
    
        
        this.dermatologistService.searchPatients(sp.firstName, sp.lastName).subscribe({
          next: patient => {
            this.myPatients = patient;
          }
    
        });
        


    }
}
