import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { User } from "../model/User";
import { Medicine } from "../model/Medicine";
import { DermatologistAppointmentDTO } from "../model/DermatologistAppointmentDTO";


@Injectable()
export class UserService {
    
    adapter: any;


    constructor(private http: HttpClient) {
    }

    public register(newUser:User){
      return this.http.post<User>("/api/user/register", newUser);
  }

    public update(updatedUser:User){
        return this.http.put("/api/user/edit", updatedUser);
    }

    public getAllergies(user_id:number):Observable<Medicine[]>{
      return this.http.get<Medicine[]>("/api/user/allergies/"+user_id);
    }

     public addAllergy(updatedUser:User, medicine_id:number){
      return this.http.put<User>("/api/user/allergy/" + medicine_id, updatedUser);
    }

    public makeDermatologistAppointment(patient:User, appointmentId:number){
      return this.http.put<number>("/api/user/makeDermatologistAppointment/" + appointmentId, patient);
    }

    public makePharmacistAppointment(patient:User, appointmentId:number){
      return this.http.put<number>("/api/user/makePharmacistAppointment/" + appointmentId, patient);
    }

    public getMyDermatologistAppointments(patientId:number):Observable<DermatologistAppointmentDTO[]>{
      return this.http.get<DermatologistAppointmentDTO[]>("/api/user/getMyDermatologistAppointments/" + patientId);
    }

    public getMyPharmacistAppointments(patientId:number):Observable<DermatologistAppointmentDTO[]>{
      return this.http.get<DermatologistAppointmentDTO[]>("/api/user/getMyPharmacistAppointments/" + patientId);
    }


    public cancelDermatologistAppointment( appointmentId:number){
      return this.http.get<number>("/api/user/cancelDermatologistAppointment/"+appointmentId);
    }
    public cancelPharmacistAppointment( appointmentId:number){
      return this.http.get<number>("/api/user/cancelPharmacistAppointment/"+ appointmentId);
    }

    public sortAppointments(pharmacyList: DermatologistAppointmentDTO[], sortType: string): Observable<DermatologistAppointmentDTO[]> {
      return this.http.post<DermatologistAppointmentDTO[]>("/api/user/sort/" + sortType, pharmacyList);
    }

}