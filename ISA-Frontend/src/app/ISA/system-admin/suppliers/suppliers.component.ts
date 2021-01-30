import { Component, OnInit } from "@angular/core";
import { Router } from "@angular/router";
import { LoginService } from "app/ISA/shared/service/login.service";
import { User } from "app/ISA/shared/model/User";
import { SupplierService } from "app/ISA/shared/service/supplier.service";
import { SystemAdminService } from "app/ISA/shared/service/system-admin.service";
import { Supplier } from "app/ISA/shared/model/Supplier";

@Component({
    selector: 'suppliers-system-admin',
    templateUrl: './suppliers.component.html'

})

export class SuppliersComponent implements OnInit {
    user: User;
    request: Request;
    newSupplier: Supplier;

    constructor(private router: Router, private loginService: LoginService, private systemAdminService: SystemAdminService) {
        this.user = new User();
        this.newSupplier = new Supplier();


    }

    ngOnInit(): void {

    }


    register() {
        console.log(this.newSupplier);
        this.systemAdminService.registerSupplier(this.newSupplier).subscribe(
            {
                next: supplier => {
                    this.newSupplier = supplier;
                    console.log(this.newSupplier);
                    if (this.newSupplier == null) {
                        alert("User with this email is already registered!");
                    }
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