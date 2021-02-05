import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { User } from "../model/User";
import { Medicine } from "../model/Medicine";
import { Orders } from "../model/Orders";
import { OrderItem } from "../model/OrderItem";
import { OrderOffer } from "../model/OrderOffer";


@Injectable()
export class SupplierService {
   
    adapter: any;


    constructor(private http: HttpClient) {
    }

   
    public update(updatedUser:User){
        return this.http.put("/api/supplier/edit", updatedUser);
    }

    public getActiveOrders(): Observable<Orders[]> {
        return this.http.get<Orders[]>("/api/supplier/activeOrders");
    }

    public getOrder(id:number): Observable<Orders> {
        return this.http.get<Orders>("/api/supplier/order/" + id);
    }

    public getOrderItems(id:number): Observable<OrderItem[]> {
        return this.http.get<OrderItem[]>("/api/supplier/orderItem/" + id);
    }

    public makeAnOffer(orderId:number, price:number,  deliveryDate:Date){
        return this.http.post<OrderOffer>("/api/supplier/offer/" + orderId + "/" + price, deliveryDate );
    }

    public getOffer(orderId:number){
        return this.http.get<OrderOffer>("/api/supplier/offer/" + orderId );
    }

    public getMyOffers(): Observable<OrderOffer[]> {
        return this.http.get<OrderOffer[]>("/api/supplier/myOffers");
    }
}