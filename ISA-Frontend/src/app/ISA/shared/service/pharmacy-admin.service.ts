import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { User } from "../model/User";
import { Medicine } from "../model/Medicine";
import { Pharmacy } from "../model/Pharmacy";
import { PharmacyAdmin } from "../model/PharmacyAdmin";
import { SearchDermatologist } from "../model/SearchDermatologist";
import { Dermatologist } from "../model/Dermatologist";
import { Pharmacist } from "../model/Pharmacist";
import { SearchPharmacist } from "../model/SearchPharmacist";
import { Orders } from "../model/Orders";
import { OrderOffer } from "../model/OrderOffer";


@Injectable()
export class PharmacyAdminService {

  constructor(private http: HttpClient) {
  }

  public update(updatedUser: number, newPassword: string) {
    return this.http.put<User>("/api/pharmacy-admin/firstlogin/" + newPassword, updatedUser);
  }

  public searchDermas(sp: SearchDermatologist): Observable<Dermatologist[]> {
    return this.http.post<Dermatologist[]>("/api/pharmacy-admin/search", sp);
  }
  public searchP(sp: SearchPharmacist): Observable<Pharmacist[]> {
    return this.http.post<Pharmacist[]>("/api/pharmacy-admin/searchP", sp);
  }

  public getPharmacyByAdminId(user_id: number): Observable<Pharmacy> {
    return this.http.get<Pharmacy>("/api/pharmacy-admin/pharmacy/getByAdminId/" + user_id);
  }

  public updateAdmin(updatedUser: PharmacyAdmin) {
    return this.http.put("/api/pharmacy-admin/edit", updatedUser);
  }

  public createDermatologistAppointment(appointmentPharmacyId: number, appointmentDermId: number, appointmentTime: string, appointmentPrice: number, appointmentDate: Date) {
    return this.http.post("/api/pharmacy-admin/dermatologistAppointment/" + appointmentPharmacyId + "/" + appointmentDermId + "/" + appointmentTime + "/" + appointmentPrice, appointmentDate);
  }
  public createPharmacistAppointment(appointmentPharmacyId: number, appointmentPharmId: number, appointmentTime: string, appointmentPrice: number, appointmentDate: Date) {
    return this.http.post("/api/pharmacy-admin/pharmacistAppointment/" + appointmentPharmacyId + "/" + appointmentPharmId + "/" + appointmentTime + "/" + appointmentPrice, appointmentDate);
  }

  public registerPharma(newPharma: Pharmacist, pharmacyId: number,) {
    return this.http.post<Pharmacist>("/api/pharmacy-admin/pharmacist/" + pharmacyId, newPharma);
  }


  public getOrders(pharmacyId: number): Observable<Orders[]> {
    return this.http.get<Orders[]>("/api/pharmacy-admin/orders/" + pharmacyId);
  }

  public getOffers(orderId: number): Observable<OrderOffer[]> {
    return this.http.get<OrderOffer[]>("/api/pharmacy-admin/offers/" + orderId);
  }


  public getOrder(id: number): Observable<Orders> {
    return this.http.get<Orders>("/api/pharmacy-admin/order/" + id);
  }


  public acceptAnOffer(id: number): Observable<OrderOffer> {
    return this.http.get<OrderOffer>("/api/pharmacy-admin/acceptOffer/" + id);
  }

  public deleteOrder(id: number): Observable<OrderOffer> {
    return this.http.delete<OrderOffer>("/api/pharmacy-admin/order/" + id);
  }


}
