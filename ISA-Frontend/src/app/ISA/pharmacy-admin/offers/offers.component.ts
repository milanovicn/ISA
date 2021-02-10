import { HttpClient } from "@angular/common/http";
import { Component, OnInit } from "@angular/core";
import { ActivatedRoute, Router } from "@angular/router";
import { OrderItem } from "app/ISA/shared/model/OrderItem";
import { OrderOffer } from "app/ISA/shared/model/OrderOffer";
import { Orders } from "app/ISA/shared/model/Orders";
import { PharmacyAdminService } from "app/ISA/shared/service/pharmacy-admin.service";


@Component({
    templateUrl: './offers.component.html',
})
export class OffersComponent implements OnInit {
    ret: Object;
    errorMessage = '';
    order: Orders;
    orderItems: OrderItem[] = [];
    id: number;
    orderPrice: number = 0;
    deliveryDate: Date = new Date();
    offers: OrderOffer[] = [];
    acceptedId = 0;
    active: boolean = false;
    filteredOffers: OrderOffer[] = [];
    filterValue:string = "";

    constructor(private httpClient: HttpClient, private route: ActivatedRoute, private router: Router, private pharmacyAdminService: PharmacyAdminService) {
        this.order = new Orders();
    
    }
    

    ngOnInit() {
        const param = this.route.snapshot.paramMap.get('id');
        if (param) {
            this.id = +param;
            this.getProduct(this.id);

        }

    }

    getProduct(id: number) {
        this.pharmacyAdminService.getOrder(id).subscribe(
            order => {
                this.order = order;
                this.getOffers(order.id);
                if (order.orderStatus == "ACTIVE") {
                    this.active = true;
                }
            }
        );
    }

    getOffers(id: number) {
        this.pharmacyAdminService.getOffers(id).subscribe(
            offers => {
                this.offers = offers;
            }
        );
    }

    acceptAnOffer() {
        this.pharmacyAdminService.acceptAnOffer(this.acceptedId).subscribe(
            ret => {
                this.ret = ret;
            }
        );
        this.refresh();

    }

    refresh() {
        window.location.reload();
    }

    deleteOrder() {
        this.pharmacyAdminService.deleteOrder(this.id).subscribe(
            ret => {
                this.ret = ret;
                this.router.navigate(["/pharmacy-admin-homepage"]);
            }
        );
    }
}