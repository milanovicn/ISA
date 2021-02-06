import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AppModule } from 'app/app.module';
import { LoginService } from 'app/ISA/shared/service/login.service';
import { User } from 'app/ISA/shared/model/User';
import { Medicine } from 'app/ISA/shared/model/Medicine';
import { MedicineService } from 'app/ISA/shared/service/medicine.service';
import { UserService } from '../../shared/service/user.service';
import { PharmacyAdminService } from 'app/ISA/shared/service/pharmacy-admin.service';
import { Pharmacy } from 'app/ISA/shared/model/Pharmacy';
import { PharmacyService } from 'app/ISA/shared/service/pharmacy.service';
import { pagespeedonline_v5 } from 'googleapis';
import { Dermatologist } from 'app/ISA/shared/model/Dermatologist';
import { Pharmacist } from 'app/ISA/shared/model/Pharmacist';
import { Actions } from 'app/ISA/shared/model/Actions';

@Component({
  selector: 'pharmacy-admin-hispharmacy',
  templateUrl: './pharmacy-admin-hispharmacy.component.html',
})
export class PharmacyAdminHisPharmacyComponent implements OnInit {
  user: User;
  myPharmacy: Pharmacy;
  myDermas: Dermatologist[] = [];
  myPharmas: Pharmacist[] = [];
  myActions: Actions[] = [];
  dateFromA:Date=new Date(); 
  dateToA:Date=new Date(); 
  pharmacyIdA:number=0;
  description:string = "";
  ret: Object;
  newActions : Actions;

  constructor(private _router: Router, private pharmacyService: PharmacyService, private pharmacyAdminService: PharmacyAdminService, private loginService: LoginService, private medicineService: MedicineService) {
    this.user = new User();
    this.myPharmacy = new Pharmacy();
    this.myDermas = [];
    this.myPharmas=[];
    this.myActions = [];
    this.newActions = new Actions();
  }

  ngOnInit(): void {
    this.getUser();


    console.log(this.user);
  }

  editPharmacy() {
    console.log("usr");
    console.log(this.user);
    this.pharmacyService.updatePharmacy(this.myPharmacy).subscribe();
    this.refresh();
  }


  getAllDermas() {
    this.pharmacyService.getDermatologists(this.myPharmacy.id).subscribe({
      next: dermatologist => {
        this.myDermas = dermatologist;
      }
    });
  }

  getAllPharmas() {
    this.pharmacyService.getPharmacists(this.myPharmacy.id).subscribe({
      next: pharmacist => {
        this.myPharmas = pharmacist;
      }
    });
  }

  getUser() {

    this.loginService.getLoggedInUser().subscribe({
      next: t => {
        this.user = t;

        console.log(this.user);
        this.getPharmacyById();
        
      }

    });

  }

  refresh() {
    window.location.reload();
  }

  getPharmacyById() {
    this.pharmacyAdminService.getPharmacyByAdminId(this.user.id).subscribe({
      next: pharmacy => {
        this.myPharmacy = pharmacy;
        this.getAllPharmas();
        this.getAllDermas();
        this.getAllActions();
        
        
      }
    });

  }

  getAllActions() {
    this.pharmacyService.getActions(this.myPharmacy.id).subscribe({
      next: actionss => {
        this.myActions = actionss;
      }
    });
  }

  addActionPharmacy(){
    console.log(this.newActions);
    this.pharmacyService.addAction(this.newActions, this.myPharmacy.id).subscribe({
      next: ret => {
        this.newActions = ret;
        console.log(this.newActions);
        this.refresh();
        
      
      }
      
    });
  
  }



}
