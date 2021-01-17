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
import { SearchDermatologist } from 'app/ISA/shared/model/SearchDermatologist';

@Component({
  selector: 'dermas-pharmacy',
  templateUrl: './dermas-pharmacy.component.html',
})
export class DermasComponent implements OnInit {
  user: User;
  myPharmacy: Pharmacy;
  myDermas: Dermatologist[] = [];
  myPharmas: Pharmacist[] = [];
  searchParameters: SearchDermatologist;



  constructor(private _router: Router, private pharmacyService: PharmacyService, private pharmacyAdminService: PharmacyAdminService, private loginService: LoginService, private medicineService: MedicineService) {
    this.user = new User();
    this.myPharmacy = new Pharmacy();
    this.myDermas = [];
    this.myPharmas = [];
    this.searchParameters = new SearchDermatologist();
  }
 
    searchDermas() {
    let sp = new SearchDermatologist();
    if (this.searchParameters.email == undefined) {
      sp.email = "all";
    } else {
      sp.email = this.searchParameters.email;
    }

    if (this.searchParameters.firstname == undefined) {
      sp.firstname = "all";
    } else {
      sp.firstname = this.searchParameters.firstname;
    }
    if (this.searchParameters.lastname == undefined) {
      sp.lastname = "all";
    } else {
      sp.lastname = this.searchParameters.lastname;
    }

    if (this.searchParameters.city == undefined) {
      sp.city = "all";
    } else {
      sp.city = this.searchParameters.city;
    }

    if (this.searchParameters.address == undefined) {
      sp.address = "all";
    } else {
      sp.address = this.searchParameters.address;
    }

    if (this.searchParameters.rateFrom == undefined) {
      sp.rateFrom = -123456789;
    } else {
      sp.rateFrom = this.searchParameters.rateFrom;
    }

    if (this.searchParameters.rateTo == undefined) {
      sp.rateTo = 123456789;
    } else {
      sp.rateFrom = this.searchParameters.rateFrom;
    }


    console.log(this.searchParameters);
    console.log(sp);

    this.pharmacyAdminService.searchDermas(sp).subscribe({
      next: dermas => {
        this.myDermas = dermas;
      }

    });
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
    this.pharmacyService.getDermatologist(this.myPharmacy.id).subscribe({
      next: dermatologist => {
        this.myDermas = dermatologist;
      }
    });
  }

  getAllPharmas() {
    this.pharmacyService.getPharmacist(this.myPharmacy.id).subscribe({
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


      }
    });

  }

}
