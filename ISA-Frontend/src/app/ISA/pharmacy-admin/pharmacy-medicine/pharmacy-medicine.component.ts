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
import { PharmacyStock } from 'app/ISA/shared/model/PharmacyStock';
import { MedicinePrice } from 'app/ISA/shared/model/MedicinePrice';
import { DateInterval } from 'app/ISA/shared/model/DateInterval';

@Component({
  selector: 'pharmacy-medicine',
  templateUrl: './pharmacy-medicine.component.html',
})
export class PharmacyMedicineComponent implements OnInit {
  user: User;
  myPharmacy: Pharmacy;
  myDermas: Dermatologist[] = [];
  myPharmas: Pharmacist[] = [];
  myMedicine: Medicine[] = [];
  searchParameters: SearchPharmacist;
  searchParameters2: SearchMedicine;
  newQuantity: number = 0;
  newMedicineId: number = 0;
  allMedications: Medicine[] = [];
  myStock: PharmacyStock[] = [];
  ret1 :Object;
  medicineIdDelete:number=0;
  medicineId:number=0;

  myActions: MedicinePrice[] = [];
 priceDates:DateInterval; 
  pharmacyIdA:number=0;
  name:string = "";
  price:number = 0;
  ret: Object;
  medicineIdP : number = 0;
  newActions : MedicinePrice;
  x:number = 0;

  constructor(private _router: Router, private pharmacyService: PharmacyService, private pharmacyAdminService: PharmacyAdminService, private loginService: LoginService, private medicineService: MedicineService) {
    this.user = new User();
    this.myPharmacy = new Pharmacy();
    this.myDermas = [];
    this.myPharmas = [];
    this.allMedications = [];
    this.myMedicine = [];
    this.searchParameters = new SearchPharmacist();
    this.searchParameters2 = new SearchMedicine();
    this.myActions = [];
    this.newActions = new MedicinePrice();
    this.priceDates = new DateInterval();
  }
  searchMedicine() {
    let sm = new SearchMedicine();
    if (this.searchParameters2.name == undefined) {
      sm.name = "all";
    } else {
      sm.name = this.searchParameters2.name;
    }

    if (this.searchParameters2.code == undefined) {
      sm.code = 0;
    } else {
      sm.code = this.searchParameters2.code;
    }

    if (this.searchParameters2.contraindications == undefined) {
      sm.contraindications = "all";
    } else {
      sm.contraindications = this.searchParameters2.contraindications;
    }

    if (this.searchParameters2.type == undefined) {
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
        this.getAllMedicines();

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
        this.getAllMedicinesInStock();
        this.getAllPrices();


      }
    });

  }
  addMedicine() {

    this.pharmacyService.addMedicineToStock(this.myPharmacy.id, this.newMedicineId, this.newQuantity).subscribe({
      next: medId => {
        this.newMedicineId = medId;

        this.refresh();
      }
    });
  }

  getAllMedicines() {
    this.medicineService.getAllMedicines().subscribe({
      next: medicines => {
        this.allMedications = medicines;
      }
    });
  }
  getAllMedicinesInStock() {
    this.pharmacyService.getAllMedicinesInStock(this.myPharmacy.id).subscribe({
      next: meds => {
        this.myStock = meds;
      }

    });
  }

  deleteMedicine(): void {
    this.pharmacyService.deleteMedicine(this.medicineIdDelete).subscribe({
      next: X => {
          this.ret1=X;
        this.refresh();
    if(this.ret1 == null){
      alert("Check the medicine list to see if you were able to delete this medicine");
    }
    else
    alert("Check the pharmacist list to see if you were able to delete this medicine");
      }
    });
}


getAllPrices() {
  this.pharmacyService.getPrices(this.myPharmacy.id).subscribe({
    next: actionss => {
      this.myActions = actionss;
    }
  });
}

addPrice(){
  console.log(this.priceDates);

  console.log(this.medicineId);
  console.log(this.price);

  this.pharmacyService.addPrice(this.myPharmacy.id, this.medicineId, this.priceDates, this.price).subscribe({
    next: ret => {
      this.ret = ret;
      this.refresh();
      if(this.ret == null){
        alert("Price for this medicine in this period is already defined!");
      }
      else
      alert("Price added successfully! ");
    }
  });

}


}
