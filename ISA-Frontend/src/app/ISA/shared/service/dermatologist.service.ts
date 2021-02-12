import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { User } from "../model/User";
import { Medicine } from "../model/Medicine";
import { DermatologistAppointmentDTO } from "../model/DermatologistAppointmentDTO";
import { Dermatologist } from "../model/Dermatologist";
import { DermatologistVacation } from "../model/DermatologistVacation";


@Injectable()
export class DermatologistService {


  adapter: any;


  constructor(private http: HttpClient) {
  }


  public update(updatedUser: User) {
    return this.http.put("/api/dermatologist/edit", updatedUser);
  }

  public availableDermatologistAppointments(dermatologistId: number): Observable<DermatologistAppointmentDTO[]> {
    return this.http.get<DermatologistAppointmentDTO[]>("/api/dermatologist/availableDermatologistAppointments/" + dermatologistId);
  }

  /*
  public makeDermatologistAppointment(patient:User, appointmentId:number){
    return this.http.put<number>("/api/dermatologist/makeDermatologistAppointment/" + appointmentId, patient);
  }
  */

  public getMyVacations(dermatologistId: number): Observable<DermatologistVacation[]> {
    return this.http.get<DermatologistVacation[]>("/api/dermatologist/myvacations/" + dermatologistId);
  }

  public addVacation(newVacation: DermatologistVacation, dermatologistId: number) {
    return this.http.post<DermatologistVacation>("/api/dermatologist/newvacation/" + dermatologistId, newVacation);
  }


  public searchPatients(firstName: String, lastName: String): Observable<User[]> {
    return this.http.post<User[]>("/api/dermatologist/search/" + firstName, lastName);
  }

  public getMyPatients(): Observable<User[]> {
    return this.http.get<User[]>("/api/dermatologist/patients");
  }


  public getReservedAppointments(): Observable<DermatologistAppointmentDTO[]> {
    return this.http.get<DermatologistAppointmentDTO[]>("/api/dermatologist/reservedAppointments");
  }

  public getReservedAndDoneAppointments(): Observable<DermatologistAppointmentDTO[]> {
    return this.http.get<DermatologistAppointmentDTO[]>("/api/dermatologist/reservedAndDoneAppointments");
  }

  public getAvailableAppointments(): Observable<DermatologistAppointmentDTO[]> {
    return this.http.get<DermatologistAppointmentDTO[]>("/api/dermatologist/availableAppointments");
  }

  public getAppointmentId(appointmentId: number): Observable<DermatologistAppointmentDTO> {
    return this.http.get<DermatologistAppointmentDTO>("/api/dermatologist/appointment/" + appointmentId);
  }

  public appointmentReserveForUser(appointmentId: number, patientId: number): Observable<Object> {
    return this.http.get<Object>("/api/dermatologist/appointmentReserveForUser/" + appointmentId + "/" + patientId);
  }

  sendInquiry(reportMedicineId: number, pharmacyId: number) {
    return this.http.get<Object>("/api/dermatologist/sendInquiry/"+pharmacyId+"/"+reportMedicineId);
  }
  checkAvailability(pharmacyId: number, reportMedicineId: number) {
    return this.http.get<boolean>("/api/dermatologist/checkAvailability/"+pharmacyId+"/"+reportMedicineId);
  }
  createReport(appointmentId: number, reportText: String, dermatologistId: number, reportMedicineId: number, reportDuration: number) {
    return this.http.post<Object>("/api/dermatologist/createReport/" + appointmentId+"/"+dermatologistId+"/"+reportMedicineId+"/"+reportDuration, reportText );
  }
  didntShowUp(appointmentId: number) {
    return this.http.get<Object>("/api/dermatologist/didntShowUp/"+appointmentId); 
  }

  getMedicineForUser(patientId: number) {
    return this.http.get<Medicine[]>("/api/dermatologist/getMedicineForUser/"+patientId);
  }



}