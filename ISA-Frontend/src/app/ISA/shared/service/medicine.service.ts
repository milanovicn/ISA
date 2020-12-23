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

    public sortMedicines(medicineList:Medicine[], sortType:string):Observable<Medicine[]>{
      return this.http.post<Medicine[]>("/api/medicine/sort/"+sortType, medicineList);
    }

    public downloadSpecifications( medicine_id:number){
      return this.http.post<number>("/api/medicine/download/" + medicine_id, medicine_id);
    }
}