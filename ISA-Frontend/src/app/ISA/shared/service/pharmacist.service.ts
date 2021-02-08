import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { User } from "../model/User";
import { Medicine } from "../model/Medicine";
import { Pharmacist } from "../model/Pharmacist";


@Injectable()
export class PharmacistService {
   
    adapter: any;


    constructor(private http: HttpClient) {
    }

   
    public update(updatedUser:User){
        return this.http.put("/api/pharmacist/edit", updatedUser);
    }

    public availablePharmacistCounseling(pharmacistId:number):Observable<Pharmacist[]>{
        return this.http.get<Pharmacist[]>("/api/pharmacist/availablePharmacistCounseling/" + pharmacistId);
      }


}