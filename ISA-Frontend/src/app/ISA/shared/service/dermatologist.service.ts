import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { User } from "../model/User";
import { Medicine } from "../model/Medicine";
import { DermatologistAppointmentDTO } from "../model/DermatologistAppointmentDTO";
import { Dermatologist } from "../model/Dermatologist";


@Injectable()
export class DermatologistService {
   
    adapter: any;


    constructor(private http: HttpClient) {
    }

   
    public update(updatedUser:User){
        return this.http.put("/api/dermatologist/edit", updatedUser);
    }

    public availableDermatologistAppointments(dermatologistId:number):Observable<DermatologistAppointmentDTO[]>{
        return this.http.get<DermatologistAppointmentDTO[]>("/api/dermatologist/availableDermatologistAppointments/" + dermatologistId);
      }

      /*
      public makeDermatologistAppointment(patient:User, appointmentId:number){
        return this.http.put<number>("/api/dermatologist/makeDermatologistAppointment/" + appointmentId, patient);
      }
      */

      
      
     public searchPatients(firstName: String, lastName: String): Observable<User[]> {
      return this.http.post<User[]>("/api/dermatologist/search/" + firstName, lastName);
     }

     public getMyPatients (): Observable<User[]> {
      return this.http.get<User[]>("/api/dermatologist/patients");
     }
     

}