import { Component, OnInit } from "@angular/core";
import { Router } from "@angular/router";
import { LoginService } from "app/ISA/shared/service/login.service";
import { User } from "app/ISA/shared/model/User";
import { SystemAdminService } from "app/ISA/shared/service/system-admin.service";
import { Medicine } from "app/ISA/shared/model/Medicine";
import { MatSelectModule } from '@angular/material/select';
import { FormControl } from "@angular/forms";
import { MedicineService } from "app/ISA/shared/service/medicine.service";

@Component({
    selector: 'medicines-system-admin',
    templateUrl: './medicines.component.html'

})

export class MedicinesComponent implements OnInit {
    user: User;
    request: Request;
    newMedicine: Medicine;
    replacements: number[] = [];
    allMedicines: Medicine[] = [];

    constructor(private router: Router, private medicineService: MedicineService, private loginService: LoginService, private systemAdminService: SystemAdminService) {
        this.user = new User();
        this.newMedicine = new Medicine();
        this.allMedicines = [];

    }

    ngOnInit(): void {
        this.getAllMedicines();
    }

    getAllMedicines() {
        this.medicineService.getAllMedicines().subscribe({
            next: medicines => {
                this.allMedicines = medicines;
            }
        });
    }

    register() {
        console.log(this.newMedicine);
        this.systemAdminService.registerMedicine(this.newMedicine).subscribe(
            {
                next: supplier => {
                    this.newMedicine = supplier;
                    console.log(this.newMedicine);
                    console.log(this.replacements);
                    if (this.newMedicine == null) {
                        alert("Medicine with this code is already registered!");
                    }
                    this.systemAdminService.addMedicineReplacements(this.replacements, this.newMedicine.id).subscribe(
                        {
                            next: replacements => {
                                this.replacements = replacements;
                            }


                        });
                }
            });

        //else {
        // this.refresh();
        //  }
    }

    refresh() {
        window.location.reload();
    }
}