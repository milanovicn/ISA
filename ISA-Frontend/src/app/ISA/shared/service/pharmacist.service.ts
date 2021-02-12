import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { User } from "../model/User";
import { Medicine } from "../model/Medicine";
import { Pharmacist } from "../model/Pharmacist";
import { PharmacistVacation } from "../model/PharmacistVacation";
import { MedicineReservation } from "../model/MedicineReservation";
import { PharmacistAppointmentDTO } from "../model/PharmacistAppointmentDTO";


@Injectable()
export class PharmacistService {

  adapter: any;


  constructor(private http: HttpClient) {
  }

  public getMyVacations(pharmacistId: number): Observable<PharmacistVacation[]> {
    return this.http.get<PharmacistVacation[]>("/api/pharmacist/myvacations/" + pharmacistId);
  }

  public addVacation(newVacation: PharmacistVacation, pharmacistId: number) {
    return this.http.post<PharmacistVacation>("/api/pharmacist/newvacation/" + pharmacistId, newVacation);
  }

  public update(updatedUser: User) {
    return this.http.put("/api/pharmacist/edit", updatedUser);
  }

  public availablePharmacistCounseling(pharmacistId: number): Observable<Pharmacist[]> {
    return this.http.get<Pharmacist[]>("/api/pharmacist/availablePharmacistCounseling/" + pharmacistId);
  }

  public checkMedicineReservationCode(reservationCode: string): Observable<MedicineReservation> {
    return this.http.get<MedicineReservation>("/api/pharmacist/checkMedicineReservationCode/" + reservationCode);

  }

  public issueMedicineReservation(reservationId: number): Observable<MedicineReservation> {
    return this.http.get<MedicineReservation>("/api/pharmacist/issueMedicineReservation/" + reservationId);

  }

  public searchPatients(firstName: String, lastName: String): Observable<User[]> {
    return this.http.post<User[]>("/api/pharmacist/search/" + firstName, lastName);
   }

   public getMyPatients (): Observable<User[]> {
    return this.http.get<User[]>("/api/pharmacist/patients");
   }
   
   public getReservedAppointments (): Observable<PharmacistAppointmentDTO[]> {
    return this.http.get<PharmacistAppointmentDTO[]>("/api/pharmacist/reservedAppointments");
   }

   public getReservedAndDoneAppointments (): Observable<PharmacistAppointmentDTO[]> {
    return this.http.get<PharmacistAppointmentDTO[]>("/api/pharmacist/reservedAndDoneAppointments");
   }

   public getAvailableAppointments (): Observable<PharmacistAppointmentDTO[]> {
    return this.http.get<PharmacistAppointmentDTO[]>("/api/pharmacist/availableAppointments");
   }

   public getAppointmentId(appointmentId: number): Observable<PharmacistAppointmentDTO> {
    return this.http.get<PharmacistAppointmentDTO>("/api/pharmacist/appointment/" + appointmentId);
   }

   public appointmentReserveForUser(appointmentId: number, patientId: number): Observable<Object> {
    return this.http.get<Object>("/api/pharmacist/appointmentReserveForUser/" + appointmentId + "/" + patientId);
   }

}