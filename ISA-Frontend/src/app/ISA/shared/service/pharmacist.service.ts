import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { User } from "../model/User";
import { Medicine } from "../model/Medicine";
import { Pharmacist } from "../model/Pharmacist";
import { PharmacistVacation } from "../model/PharmacistVacation";


@Injectable()
export class PharmacistService {
   
    adapter: any;


    constructor(private http: HttpClient) {
    }

    public getMyVacations(pharmacistId: number): Observable<PharmacistVacation[]> {
      return this.http.get<PharmacistVacation[]>("/api/pharmacist/myvacations/" + pharmacistId);
    }
   
    public addVacation(newVacation: PharmacistVacation, pharmacistId: number) {
      return this.http.post<PharmacistVacation>("/api/pharmacist/newvacation/"+ pharmacistId , newVacation);
    }
    
    public update(updatedUser:User){
        return this.http.put("/api/pharmacist/edit", updatedUser);
    }

    public availablePharmacistCounseling(pharmacistId:number):Observable<Pharmacist[]>{
        return this.http.get<Pharmacist[]>("/api/pharmacist/availablePharmacistCounseling/" + pharmacistId);
      }

}