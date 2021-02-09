import { HttpClient } from "@angular/common/http";
import { Component, OnInit } from "@angular/core";
import { ActivatedRoute, Router } from "@angular/router";
import { OrderItem } from "app/ISA/shared/model/OrderItem";
import { OrderOffer } from "app/ISA/shared/model/OrderOffer";
import { Orders } from "app/ISA/shared/model/Orders";
import { SupplierService } from "app/ISA/shared/service/supplier.service";


@Component({
    templateUrl: './supplier-order.component.html',
})
export class SupplierOrderComponent implements OnInit {
    ret:Object;
    errorMessage = '';
    order: Orders;
    orderItems: OrderItem[] = [];
    id: number;
    orderPrice:number = 0;
    deliveryDate:Date = new Date();
    myOffer:OrderOffer;

    constructor(private httpClient: HttpClient,private route: ActivatedRoute, private router: Router, private supplierService: SupplierService) {
        this.order = new Orders();
        this.myOffer = new OrderOffer();
    }


    ngOnInit() {
        const param = this.route.snapshot.paramMap.get('id');
        if (param) {
            this.id = +param;
            this.getProduct(this.id);

        }

    }

    getProduct(id: number) {
        this.supplierService.getOrder(id).subscribe(
            order => {
              this.order = order;
              this.getOrderItems(order.id);
              this.getMyOffer();
            }
          );
    }

    getOrderItems(id:number){

        this.supplierService.getOrderItems(id).subscribe(
            orderItms => {
              this.orderItems = orderItms;
            }
          );
    }

    getMyOffer(){

        this.supplierService.getOffer(this.order.id).subscribe(
            myOffer => {
              this.myOffer = myOffer;
              console.log(myOffer);
              if(myOffer == null){
                  this.myOffer = new OrderOffer();
              }
            }
          );
    }

    placeAnOffer(){
        this.supplierService.makeAnOffer(this.order.id, this.myOffer.price, this.myOffer.deliveryDate).subscribe(
            ret => {
              this.ret = ret;
            }
          );

    }

}