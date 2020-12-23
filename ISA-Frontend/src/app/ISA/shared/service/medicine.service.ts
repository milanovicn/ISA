import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { User } from "../model/User";
import { Medicine } from "../model/Medicine";
import { SearchMedicine } from "../model/SearchMedicine";


@Injectable()
export class MedicineService {
    constructor(private http: HttpClient) {
    }

    public getAllMedicines():Observable<Medicine[]>{
      return this.http.get<Medicine[]>("/api/medicine");
    }

    public searchMedicine(sm:SearchMedicine):Observable<Medicine[]>{
      return this.http.post<Medicine[]>("/api/medicine/search", sm);
    }

}