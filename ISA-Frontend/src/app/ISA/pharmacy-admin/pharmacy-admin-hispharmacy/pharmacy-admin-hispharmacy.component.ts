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

@Component({
  selector: 'pharmacy-admin-hispharmacy',
  templateUrl: './pharmacy-admin-hispharmacy.component.html',
})
export class PharmacyAdminHisPharmacyComponent implements OnInit {
  user: User;
  myPharmacy: Pharmacy;
  //myPh : Pharmacy;


  constructor(private _router: Router,private pharmacyService: PharmacyService,private pharmacyAdminService: PharmacyAdminService, private loginService: LoginService, private medicineService: MedicineService) {
    this.user = new User();
    this.myPharmacy = new Pharmacy();
    //this.myPh = new Pharmacy();
  }

  ngOnInit(): void {
    this.getUser();

    
    console.log(this.user);
  }

  editPharmacy() {
    console.log("usr");
    console.log(this.user);
    this.pharmacyService.updatePharmacy(this.myPharmacy).subscribe();
   // this.refresh();
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


  refresh(){
    window.location.reload();
  }
  getPharmacyById(){
    this.pharmacyAdminService.getPharmacyByAdminId(this.user.id).subscribe({
        next: pharmacy => {
       this.myPharmacy = pharmacy;
        }
    });

  }
 
}
