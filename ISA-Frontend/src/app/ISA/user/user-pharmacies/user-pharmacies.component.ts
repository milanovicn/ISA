
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Pharmacy } from 'app/ISA/shared/model/Pharmacy';
import { SearchPharmacy } from 'app/ISA/shared/model/SearchPharmacy';
import { PharmacyService } from 'app/ISA/shared/service/pharmacy.service';


@Component({
    selector: 'user-pharmacies',
    templateUrl: './user-pharmacies.component.html'
})


export class UserPharmaciesComponent implements OnInit {
    allPharmacies : Pharmacy[] = [];
    searchParameters  : SearchPharmacy;
    
    appointmentTime: string = "";
    appointmentDate: Date = new Date();


    constructor( private router: Router, private pharmacyService: PharmacyService) { 
        this.searchParameters = new SearchPharmacy();
        
    } 
  
   


    ngOnInit(): void {
        this.pharmacyService.getAllPhamracies().subscribe({
            next: pharmacies => {
                this.allPharmacies = pharmacies;
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