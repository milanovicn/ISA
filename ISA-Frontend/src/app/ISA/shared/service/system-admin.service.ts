import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { User } from "../model/User";
import { Medicine } from "../model/Medicine";
import { SupplierHomepageComponent } from "app/ISA/supplier/supplier-homepage/supplier-homepage.component";
import { Supplier } from "../model/Supplier";
import { Pharmacy } from "../model/Pharmacy";
import { PharmacyAdmin } from "../model/PharmacyAdmin";
import { Complaint } from "../model/Complaint";


@Injectable()
export class SystemAdminService {
  
    adapter: any;


    constructor(private http: HttpClient) {
    }

    public registerSupplier(newS: Supplier) {
        return this.http.post<Supplier>("/api/system-admin/supplier", newS);
    }

    public registerMedicine(newM: Medicine) {
        return this.http.post<Medicine>("/api/system-admin/medicine", newM);
    }

    public addMedicineReplacements(replacements: number[], medicineId:number) {
        return this.http.post<number[]>("/api/system-admin/replacements/" + medicineId, replacements);
    }

    public registerSystemAdmin(newSysAdmin: User) {
        return this.http.post<User>("/api/system-admin", newSysAdmin);
    }

    
    public registerPharmacy(newPharmacy: Pharmacy) {
        return this.http.post<Pharmacy>("/api/system-admin/pharmacy", newPharmacy);
    }

    public registerPharmacyAdmin(newPhAdmin: PharmacyAdmin, pharmacyId:number) {
        return this.http.post<PharmacyAdmin>("/api/system-admin/pharmacy-admin/" + pharmacyId, newPhAdmin);
    }

    public answerComplaint(commplaintId: number, answer:string): Observable<Complaint> {
        console.log(answer);
        console.log(commplaintId);
        return this.http.post<Complaint>("/api/system-admin/answerComplaint/" + commplaintId, answer);
    }

    public getUnanswered():Observable<Complaint[]>  {
        return this.http.get<Complaint[]>("/api/system-admin/complaints");
    }

    

}