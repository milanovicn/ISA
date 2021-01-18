import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { Dermatologist } from "../model/Dermatologist";
import { Medicine } from "../model/Medicine";
import { Pharmacist } from "../model/Pharmacist";
import { Pharmacy } from "../model/Pharmacy";
import { SearchPharmacy } from "../model/SearchPharmacy";


@Injectable()
export class PharmacyService {
    constructor(private http: HttpClient) {
    }

    public getAllPhamracies():Observable<Pharmacy[]>{
      return this.http.get<Pharmacy[]>("/api/pharmacy");
    }

    public searchPharmacies(sp:SearchPharmacy):Observable<Pharmacy[]>{
      return this.http.post<Pharmacy[]>("/api/pharmacy/search", sp);
    }

    public sortPharmacies(pharmacyList:Pharmacy[], sortType:string):Observable<Pharmacy[]>{
      return this.http.post<Pharmacy[]>("/api/pharmacy/sort/"+sortType, pharmacyList);
    }

    public updatePharmacy(updatedPharmacy:Pharmacy){
      return this.http.put("/api/pharmacy/editPharmacy", updatedPharmacy);
  }
  public getDermatologist(pharmacyId:number):Observable<Dermatologist[]>{
    return this.http.get<Dermatologist[]>("/api/pharmacy/mydermas/"+pharmacyId);
  }
  public getPharmacist(pharmacyId:number):Observable<Pharmacist[]>{
    return this.http.get<Pharmacist[]>("/api/pharmacy/mypharmas/"+pharmacyId);
  }
  public getMedicine(pharmacyId:number):Observable<Medicine[]>{
    return this.http.get<Medicine[]>("/api/pharmacy/mymedicine/"+pharmacyId);
  }
  public addMedicine(updatedUser:Pharmacy, medicine_id:number){
    return this.http.put<Pharmacy>("/api/pharmacy/addmedicine/" + medicine_id, updatedUser);
  }

  
}