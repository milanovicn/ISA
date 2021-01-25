import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { User } from "../model/User";
import { Medicine } from "../model/Medicine";


@Injectable()
export class SupplierService {
   
    adapter: any;


    constructor(private http: HttpClient) {
    }

   
    public update(updatedUser:User){
        return this.http.put("/api/supplier/edit", updatedUser);
    }


}