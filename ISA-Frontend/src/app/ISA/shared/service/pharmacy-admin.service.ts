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
import { OrderItem } from "../model/OrderItem";
import { DermatologistVacation } from "../model/DermatologistVacation";
import { PharmacistVacation } from "../model/PharmacistVacation";
import { Inquiry } from "../model/Inquiry";


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

  public getAllOrders(pharmacyId: number): Observable<Orders[]> {
    return this.http.get<Orders[]>("/api/pharmacy-admin/allorders/" + pharmacyId);
  }

  public getAllDermaVacations(pharmacyId: number): Observable<DermatologistVacation[]> {
    return this.http.get<DermatologistVacation[]>("/api/pharmacy-admin/allVacationsDermatologist/" + pharmacyId);
  }

  public getAllPharmaVacations(pharmacyId: number): Observable<PharmacistVacation[]> {
    return this.http.get<PharmacistVacation[]>("/api/pharmacy-admin/allVacationsPharmacist/" + pharmacyId);
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

  public createOrder(newOrder: Orders) {
    return this.http.post<Orders>("/api/pharmacy-admin/order", newOrder);
  }

  public createOrderItems(items: OrderItem[]) {
    return this.http.post<number>("/api/pharmacy-admin/orderItems", items);
  }
  public acceptVP(idVacation: number): Observable<PharmacistVacation> {
    return this.http.get<PharmacistVacation>("/api/pharmacy-admin/acceptVacationPharmacist/" + idVacation);
  }
  public acceptVD(idVacation: number): Observable<DermatologistVacation> {
    return this.http.get<DermatologistVacation>("/api/pharmacy-admin/acceptVacationDermatologist/" + idVacation);
  }
  public rejectVD(idVacation: number, opis: string): Observable<DermatologistVacation> {
    return this.http.get<DermatologistVacation>("/api/pharmacy-admin/rejectVacationDermatologist/" + idVacation + "/" + opis);
  }
  public rejectVP(idVacation: number, opis: string): Observable<PharmacistVacation> {
    return this.http.get<PharmacistVacation>("/api/pharmacy-admin/rejectVacationPharmacist/" + idVacation + "/" + opis);
  }
  public getInquiries(pharmacyId: number) {
    return this.http.get<Inquiry[]>("/api/pharmacy-admin/inquiry/" + pharmacyId);
  }

}
