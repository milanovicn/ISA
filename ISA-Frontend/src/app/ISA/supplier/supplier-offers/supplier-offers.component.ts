import { Component, OnInit } from "@angular/core";
import { ActivatedRoute, Router } from "@angular/router";
import { OrderOffer } from "app/ISA/shared/model/OrderOffer";
import { Orders } from "app/ISA/shared/model/Orders";
import { SupplierService } from "app/ISA/shared/service/supplier.service";

@Component({

    templateUrl: './supplier-offers.component.html'
  
  })
  
  export class SupplierOffersComponent implements OnInit {
  
    myOffers: OrderOffer[] = [];
    filteredOffers: OrderOffer[] = [];
    filterValue:string = "";
  
    constructor(private route: ActivatedRoute, private router: Router, private supplierService: SupplierService) {
  
  
    }

    ngOnInit(): void {
        
      this.supplierService.getMyOffers().subscribe(
        myOffers => {
            console.log("uso");
          this.myOffers = myOffers;
          this.filteredOffers = myOffers;
        }
      );
    }
  
  filter(){
    this.filteredOffers = [];
    for(let i=0; i<this.myOffers.length; i++){
      if(this.myOffers[i].status==this.filterValue){
        this.filteredOffers.push(this.myOffers[i]);
      }
    }

  }
  
  
}