import { Component, OnInit } from "@angular/core";
import { Router } from "@angular/router";
import { LoginService } from "app/ISA/shared/service/login.service";
import { User } from "app/ISA/shared/model/User";
import { SupplierService } from "app/ISA/shared/service/supplier.service";
import { SystemAdminService } from "app/ISA/shared/service/system-admin.service";
import { Supplier } from "app/ISA/shared/model/Supplier";
import { Pharmacy } from "app/ISA/shared/model/Pharmacy";

@Component({
    selector: 'pharmacy-system-admin',
    templateUrl: './new-pharmacy.component.html'

})

export class NewPharmacyComponent implements OnInit {
    user: User;
    request: Request;
    newPharmacy: Pharmacy;

    constructor(private router: Router, private loginService: LoginService, private systemAdminService: SystemAdminService) {
        this.user = new User();
        this.newPharmacy = new Pharmacy();


    }

    ngOnInit(): void {

    }


    register() {
        console.log(this.newPharmacy);
        this.systemAdminService.registerPharmacy(this.newPharmacy).subscribe(
            {
                next: pharmacy => {
                    this.newPharmacy = pharmacy;
                    console.log(this.newPharmacy);
                   
                }
            });
       
 
    }

    refresh() {
        window.location.reload();
    }
}