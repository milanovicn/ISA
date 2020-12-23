import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
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
  
}