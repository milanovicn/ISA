import { Component, OnInit } from "@angular/core";
import { ActivatedRoute, Router } from "@angular/router";
import { Orders } from "app/ISA/shared/model/Orders";
import { SupplierService } from "app/ISA/shared/service/supplier.service";

@Component({

    templateUrl: './supplier-orders.component.html'
  
  })
  
  export class SupplierOrdersComponent implements OnInit {
  
    activeOrders: Orders[] = [];
  
  
    constructor(private route: ActivatedRoute, private router: Router, private supplierService: SupplierService) {
  
  
    }
    ngOnInit(): void {
      this.supplierService.getActiveOrders().subscribe(
        activeOrders => {
          this.activeOrders = activeOrders;
        }
      );
    }
  
  
  
  
}