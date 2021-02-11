import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Medicine } from 'app/ISA/shared/model/Medicine';
import { Pharmacy } from 'app/ISA/shared/model/Pharmacy';
import { SearchMedicine } from 'app/ISA/shared/model/SearchMedicine';
import { SearchPharmacy } from 'app/ISA/shared/model/SearchPharmacy';
import { MedicineService } from 'app/ISA/shared/service/medicine.service';
import { PharmacyService } from 'app/ISA/shared/service/pharmacy.service';


@Component({
    selector: 'medicines',
    templateUrl: './all-medicines.component.html'
})


export class AllMedicinesComponent implements OnInit {
    allMedicines : Medicine[] = [];
    searchParameters  : SearchMedicine;
    downloadMedicine : number;

    constructor(private router: Router, private medicineService: MedicineService) { 
        this.searchParameters = new SearchMedicine();
        this.downloadMedicine =0;
    }

    ngOnInit(): void {
        this.medicineService.getAllMedicines().subscribe({
            next: meds => {
                this.allMedicines = meds;
            }

        });

    }
    refresh() {
        window.location.reload();
      }

    searchMedicine(){
        let sm = new SearchMedicine();
        if(this.searchParameters.name == undefined){
            sm.name = "all";
        } else {
            sm.name = this.searchParameters.name;
        }

        if(this.searchParameters.code == undefined){
            sm.code = 0;
        } else {
            sm.code = this.searchParameters.code;
        }

        if(this.searchParameters.contraindications == undefined){
            sm.contraindications = "all";
        } else {
            sm.contraindications = this.searchParameters.contraindications;
        }

        if(this.searchParameters.type == undefined){
            sm.type = "all";
        } else {
            sm.type = this.searchParameters.type;
        }


        console.log(this.searchParameters);
        console.log(sm);

        this.medicineService.searchMedicine(sm).subscribe({
            next: meds => {
                this.allMedicines = meds;
            }

        });

       
    }

    sort(sortType:string){
        console.log(sortType);
        this.medicineService.sortMedicines(this.allMedicines, sortType).subscribe({
            next: meds => {
                this.allMedicines = meds;
            }

        });
    }

    downloadSpecifications(){
    
        console.log(this.downloadMedicine);
        this.medicineService.downloadSpecifications(this.downloadMedicine).subscribe();
      }
}