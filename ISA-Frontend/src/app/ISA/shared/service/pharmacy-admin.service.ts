import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { User } from "../model/User";
import { Medicine } from "../model/Medicine";
import { Pharmacy } from "../model/Pharmacy";
import { PharmacyAdmin } from "../model/PharmacyAdmin";


@Injectable()
export class PharmacyAdminService {
  
    constructor(private http: HttpClient) {
    }

    public update(updatedUser:number,newPassword:string){
        return this.http.put<User>("/api/pharmacy-admin/firstlogin/"+newPassword, updatedUser);
    }

    
    public getPharmacyByAdminId(user_id:number):Observable<Pharmacy>{
        return this.http.get<Pharmacy>("/api/pharmacy-admin/pharmacy/getByAdminId/"+user_id);
      }
   
      public updateAdmin(updatedUser:PharmacyAdmin){
        return this.http.put("/api/pharmacy-admin/edit", updatedUser);
    }

}