import { Component, OnInit } from "@angular/core";
import { Router } from "@angular/router";
import { LoginService } from "app/ISA/shared/service/login.service";
import { User } from "app/ISA/shared/model/User";
import { SupplierService } from "app/ISA/shared/service/supplier.service";
import { SystemAdminService } from "app/ISA/shared/service/system-admin.service";
import { Supplier } from "app/ISA/shared/model/Supplier";
import { Pharmacy } from "app/ISA/shared/model/Pharmacy";
import { PharmacyService } from "app/ISA/shared/service/pharmacy.service";

@Component({
    selector: 'new-pharmacy-admin',
    templateUrl: './new-pharmacy-admin.component.html'

})

export class NewPharmacyAdminComponent implements OnInit {
    user: User;
    request: Request;
    newPharmacyAdmin: User;
    pharmacyId: number = 0;
    allPharmacies: Pharmacy[] = [];

    constructor(private router: Router, private loginService: LoginService, 
        private systemAdminService: SystemAdminService, private pharmacyService:PharmacyService) {
        this.user = new User();
        this.newPharmacyAdmin = new User();
        this.allPharmacies = [];

    }

    ngOnInit(): void {
        this.getAllPharmacies();
    }

    getAllPharmacies() {
        this.pharmacyService.getAllPhamracies().subscribe({
            next: pharmacies => {
                this.allPharmacies = pharmacies;
            }
        });
    }

    register() {
        console.log(this.newPharmacyAdmin);
        this.systemAdminService.registerPharmacyAdmin(this.newPharmacyAdmin, this.pharmacyId).subscribe(
            {
                next: phAdmin => {
                    this.newPharmacyAdmin = phAdmin;
                    console.log(this.newPharmacyAdmin);
                    if (this.newPharmacyAdmin == null) {
                        alert("User with this email is already registered!");
                    }
                }
            });
       
       
    }

    refresh() {
        window.location.reload();
    }
}