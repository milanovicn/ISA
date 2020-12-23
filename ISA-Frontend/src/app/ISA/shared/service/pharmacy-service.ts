import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { User } from "../model/User";
import { Medicine } from "../model/Medicine";
import { Pharmacy } from "../model/Pharmacy";


@Injectable()
export class PharmacyService {
    

    constructor(private http: HttpClient) {
    }

    public updatePharmacy(updatedPharmacy:Pharmacy){
        return this.http.put("/api/pharmacy/editPharmacy", updatedPharmacy);
    }

 

}