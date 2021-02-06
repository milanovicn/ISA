import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { Actions } from "../model/Actions";
import { Dermatologist } from "../model/Dermatologist";
import { DermatologistAppointmentDTO } from "../model/DermatologistAppointmentDTO";
import { Medicine } from "../model/Medicine";
import { Pharmacist } from "../model/Pharmacist";
import { Pharmacy } from "../model/Pharmacy";
import { PharmacyStock } from "../model/PharmacyStock";
import { SearchPharmacy } from "../model/SearchPharmacy";


@Injectable()
export class PharmacyService {
    constructor(private http: HttpClient) {
    }

    public getAllPhamracies():Observable<Pharmacy[]>{
      return this.http.get<Pharmacy[]>("/api/pharmacy");
    }

    public getPharmacy(id:number):Observable<Pharmacy>{
      return this.http.get<Pharmacy>("/api/pharmacy/" + id);
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
  public getDermatologists(pharmacyId:number):Observable<Dermatologist[]>{
    return this.http.get<Dermatologist[]>("/api/pharmacy/mydermas/"+pharmacyId);
  }
  public getPharmacists(pharmacyId:number):Observable<Pharmacist[]>{
    return this.http.get<Pharmacist[]>("/api/pharmacy/mypharmas/"+pharmacyId);
  }
  public getMedicine(pharmacyId:number):Observable<Medicine[]>{
    return this.http.get<Medicine[]>("/api/pharmacy/mymedicine/"+pharmacyId);
  }

  public getActions(pharmacyId:number):Observable<Actions[]>{
    return this.http.get<Actions[]>("/api/pharmacy/myactions/"+pharmacyId);
  }

  public addMedicine(updatedUser:Pharmacy, medicine_id:number){
    return this.http.put<Pharmacy>("/api/pharmacy/addMedicine/" + medicine_id, updatedUser);
  }
  
  public addMedicineToStock(pharmacyId:number, newMedicineId:number,  newQuantity:number){
    return this.http.post<number>("/api/pharmacy/addMedicineToStock/" + pharmacyId + "/" + newMedicineId, newQuantity );
  }

  public getAvailableDermatologists(pharmacyId:number):Observable<Dermatologist[]>{
    return this.http.get<Dermatologist[]>("/api/pharmacy/availableDermatologists/"+pharmacyId);
  }

  public addDermatologist(pharmacyId:number, dermatologistId:number, workDays:string[]){
    return this.http.put<number>("/api/pharmacy/addDermatologist/"+pharmacyId+"/"+dermatologistId, workDays);
  }

  public getAllMedicinesInStock(pharmacyId:number):Observable<PharmacyStock[]>{
    return this.http.get<PharmacyStock[]>("/api/pharmacy/medicinesInStock/"+pharmacyId);
  }


  public availableDermatologistAppointments(pharmacyId:number):Observable<DermatologistAppointmentDTO[]>{
    return this.http.get<DermatologistAppointmentDTO[]>("/api/pharmacy/availableDermatologistAppointments/" + pharmacyId);
  }

  public registerPharma(newPharma: Pharmacist, pharmacyId:number, workDays:string[] ) {
    return this.http.post<Pharmacist>("/api/pharmacy-admin/pharmacist/" + newPharma + "/"+pharmacyId, workDays);
  }

  public addPharmacist(pharmacyId:number, pharmacistId:number, workDays:string[]){
    return this.http.put<number>("/api/pharmacy/addPharmacist/"+pharmacyId+"/"+pharmacistId, workDays);
  }

  public deletePharma(pharmacistIdDelete: number) {
    return this.http.delete("/api/pharmacist/" + pharmacistIdDelete);
}
public deleteDerma(dermatologistIdDelete: number) {
  return this.http.delete("/api/dermatologist/" + dermatologistIdDelete);
}

public addAction(newAction: Actions, pharmacyId:number, ) {
  return this.http.post<Actions>("/api/pharmacy/newAction/" + pharmacyId, newAction);
}

}
