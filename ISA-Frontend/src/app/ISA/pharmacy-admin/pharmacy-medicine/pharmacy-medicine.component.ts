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
import { SearchPharmacist } from 'app/ISA/shared/model/SearchPharmacist';
import { SearchMedicine } from 'app/ISA/shared/model/SearchMedicine';

@Component({
  selector: 'pharmacy-medicine',
  templateUrl: './pharmacy-medicine.component.html',
})
export class PharmacyMedicineComponent implements OnInit {
  user: User;
  myPharmacy: Pharmacy;
  myDermas: Dermatologist[] = [];
  myPharmas: Pharmacist[] = [];
  myMedicine : Medicine[] = [];
  searchParameters: SearchPharmacist;
  searchParameters2  : SearchMedicine;


  constructor(private _router: Router, private pharmacyService: PharmacyService, private pharmacyAdminService: PharmacyAdminService, private loginService: LoginService, private medicineService: MedicineService) {
    this.user = new User();
    this.myPharmacy = new Pharmacy();
    this.myDermas = [];
    this.myPharmas=[];
    this.myMedicine = [];
    this.searchParameters = new SearchPharmacist();
    this.searchParameters2 = new SearchMedicine();
  }
  searchMedicine(){
    let sm = new SearchMedicine();
    if(this.searchParameters2.name == undefined){
        sm.name = "all";
    } else {
        sm.name = this.searchParameters2.name;
    }

    if(this.searchParameters2.code == undefined){
        sm.code = 0;
    } else {
        sm.code = this.searchParameters2.code;
    }

    if(this.searchParameters2.contraindications == undefined){
        sm.contraindications = "all";
    } else {
        sm.contraindications = this.searchParameters2.contraindications;
    }

    if(this.searchParameters2.type == undefined){
        sm.type = "all";
    } else {
        sm.type = this.searchParameters2.type;
    }


    console.log(this.searchParameters);
    console.log(sm);

    this.medicineService.searchMedicine(sm).subscribe({
        next: meds => {
            this.myMedicine = meds;
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
  
  searchPharmas() {
    let sp = new SearchPharmacist();
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

    this.pharmacyAdminService.searchP(sp).subscribe({
      next: pharmas => {
        this.myPharmas = pharmas;
      }

    });
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
  getAllMedicine() {
    this.pharmacyService.getMedicine(this.myPharmacy.id).subscribe({
      next: medicine => {
        this.myMedicine = medicine;
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
        this.getAllMedicine();
        
        
      }
    });

  }

}