import { Component, OnInit } from "@angular/core";
import { ActivatedRoute, Router } from "@angular/router";
import { Orders } from "app/ISA/shared/model/Orders";
import { Pharmacy } from "app/ISA/shared/model/Pharmacy";
import { User } from "app/ISA/shared/model/User";
import { LoginService } from "app/ISA/shared/service/login.service";
import { PharmacyAdminService } from "app/ISA/shared/service/pharmacy-admin.service";

@Component({

    templateUrl: './orders.component.html'
  
  })
  
  export class OrdersComponent implements OnInit {
  
    activeOrders: Orders[] = [];
    user:User;
    myPharmacy: Pharmacy;
  
  
    constructor(private route: ActivatedRoute, private router: Router, private loginService: LoginService,
        private pharmacyAdminService: PharmacyAdminService) {
            this.user = new User();
            this.myPharmacy = new Pharmacy();
  
    }

    ngOnInit(): void {

        this.getUser();
     
    }
  
  
  getOrders(){
    this.pharmacyAdminService.getOrders(this.myPharmacy.id).subscribe(
        activeOrders => {
          this.activeOrders = activeOrders;
        }
      );

  }

  getUser() {

    this.loginService.getLoggedInUser().subscribe({
      next: t => {
        this.user = t;

        console.log(this.user);
        this.getPharmacyById();
        
      }

    });

  }

  getPharmacyById() {
    this.pharmacyAdminService.getPharmacyByAdminId(this.user.id).subscribe({
      next: pharmacy => {
        this.myPharmacy = pharmacy;
        this.getOrders();
        
        
      }
    });

  }
  
}