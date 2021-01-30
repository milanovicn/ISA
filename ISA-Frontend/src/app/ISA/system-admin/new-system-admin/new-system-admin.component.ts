import { Component, OnInit } from "@angular/core";
import { Router } from "@angular/router";
import { LoginService } from "app/ISA/shared/service/login.service";
import { User } from "app/ISA/shared/model/User";
import { SupplierService } from "app/ISA/shared/service/supplier.service";
import { SystemAdminService } from "app/ISA/shared/service/system-admin.service";
import { Supplier } from "app/ISA/shared/model/Supplier";

@Component({
    selector: 'new-system-admin',
    templateUrl: './new-system-admin.component.html'

})

export class NewSystemAdminComponent implements OnInit {
    user: User;
    request: Request;
    newSysAdmin:User;

    constructor(private router: Router, private loginService: LoginService, private systemAdminService: SystemAdminService) {
        this.user = new User();
        this.newSysAdmin = new User();


    }

    ngOnInit(): void {

    }


    register() {
        console.log(this.newSysAdmin);
        this.systemAdminService.registerSystemAdmin(this.newSysAdmin).subscribe(
            {
                next: supplier => {
                    this.newSysAdmin = supplier;
                    console.log(this.newSysAdmin);
                    if (this.newSysAdmin == null) {
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