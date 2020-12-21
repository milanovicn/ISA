import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { User } from "../model/User";
import { Medicine } from "../model/Medicine";


@Injectable()
export class PharmacyAdminService {
  
    constructor(private http: HttpClient) {
    }

    public update(updatedUser:number,newPassword:string){
        return this.http.put<User>("/api/pharmacy-admin/firstlogin/"+newPassword, updatedUser);
    }


}