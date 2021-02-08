
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Pharmacy } from 'app/ISA/shared/model/Pharmacy';
import { SearchPharmacy } from 'app/ISA/shared/model/SearchPharmacy';
import { User } from 'app/ISA/shared/model/User';
import { LoginService } from 'app/ISA/shared/service/login.service';
import { PharmacyService } from 'app/ISA/shared/service/pharmacy.service';
import { UserService } from 'app/ISA/shared/service/user.service';
import { UserModule } from '../user.module';


@Component({
    selector: 'user-pharmacies',
    templateUrl: './user-pharmacies.component.html'
})


export class UserPharmaciesComponent implements OnInit {
    allPharmacies : Pharmacy[] = [];
    searchParameters  : SearchPharmacy;
    subscriptionPharmacies: Pharmacy[] = [];
    appointmentTime: string = "";
    appointmentDate: Date = new Date();
    user:User;

    constructor( private router: Router, private pharmacyService: PharmacyService, private loginService:LoginService,
        private userService:UserService) { 
        this.searchParameters = new SearchPharmacy();
        this.user = new User();
    } 
  
   


    ngOnInit(): void {
        this.pharmacyService.getAllPhamracies().subscribe({
            next: pharmacies => {
                this.allPharmacies = pharmacies;
                this.getUser();
            }

        });

    }

    getUser() {
        this.loginService.getLoggedInUser().subscribe({
            next: user => {
                this.user = user;
                this.getSubscriptions();
              
            }
        });
    }
    getSubscriptions() {
        this.userService.getSubscriptions(this.user.id).subscribe({
            next: subscriptionPharmacies => {
                this.subscriptionPharmacies = subscriptionPharmacies;
            }

        });
    }

    searchPharmacy(){
        let sp = new SearchPharmacy();
        if(this.searchParameters.description == undefined){
            sp.description = "all";
        } else {
            sp.description = this.searchParameters.description;
        }

        if(this.searchParameters.name == undefined){
            sp.name = "all";
        } else {
            sp.name = this.searchParameters.name;
        }

        if(this.searchParameters.city == undefined){
            sp.city = "all";
        } else {
            sp.city = this.searchParameters.city;
        }

        if(this.searchParameters.address == undefined){
            sp.address = "all";
        } else {
            sp.address = this.searchParameters.address;
        }

        if(this.searchParameters.rateFrom == undefined){
           sp.rateFrom = -123456789;
        } else {
            sp.rateFrom = this.searchParameters.rateFrom;
        }

        if(this.searchParameters.rateTo == undefined){
            sp.rateTo = 123456789;
        } else {
            sp.rateFrom = this.searchParameters.rateFrom;
        }


        console.log(this.searchParameters);
        console.log(sp);

        this.pharmacyService.searchPharmacies(sp).subscribe({
            next: pharmacies => {
                this.allPharmacies = pharmacies;
            }

        });
    }

   
    
    sort(sortType:string){
        console.log(sortType);
        this.pharmacyService.sortPharmacies(this.allPharmacies, sortType).subscribe({
            next: pharmacies => {
                this.allPharmacies = pharmacies;
            }

        });
    }


    searchPharmacyByTimeForCounseling() {
        this.pharmacyService.searchPharmacyByTimeForCounseling(this.appointmentTime, this.appointmentDate).subscribe({
            next: pharmacies => {
                this.allPharmacies = pharmacies;
            }

        });

    }
}