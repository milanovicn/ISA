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
  searchParameters: SearchDermatologist;
  availableDermatologists: Dermatologist[] = [];
  workDays: string[] = [];
  dermatologistId:number = 0;
  dermatologistIdDelete:number=0;
  appointmentDermId:number=0;
  appointmentTime:string = "";
  appointmentDate:Date=new Date(); 
  appointmentPrice:number=0;
  ret: Object;

  constructor(private _router: Router, private pharmacyService: PharmacyService, private pharmacyAdminService: PharmacyAdminService, private loginService: LoginService, private medicineService: MedicineService) {
    this.user = new User();
    this.myPharmacy = new Pharmacy();
    this.myDermas = [];
    this.searchParameters = new SearchDermatologist();
    this.availableDermatologists = [];
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
    if(this.searchParameters.address == undefined && this.searchParameters.city == undefined
      && this.searchParameters.email == undefined && this.searchParameters.firstname == undefined
      && this.searchParameters.lastname  == undefined && this.searchParameters.rateFrom == undefined &&  this.searchParameters.rateTo== undefined)
       {
        alert("You did not enter any parameter!");
        this.refresh();
       }
      

    console.log(this.searchParameters);
    console.log(sp);

    this.pharmacyAdminService.searchDermas(sp).subscribe({
      next: dermas => {
        this.myDermas = dermas;
      }

    });
  }

  deleteDerma(): void {
    this.pharmacyService.deleteDerma(this.dermatologistIdDelete).subscribe({
      next: X => {
        this.ret = X;
        this.refresh();
    if(this.ret == null){
      alert("Check the pharmacist list to see if you were able to delete this pharmacist");
    }
    else
    alert("Check the pharmacist list to see if you were able to delete this pharmacist");
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


  getMyDermas() {
    this.pharmacyService.getDermatologists(this.myPharmacy.id).subscribe({
      next: dermatologist => {
        this.myDermas = dermatologist;
      }
    });
  }

  getAvailableDermatologists() {
    this.pharmacyService.getAvailableDermatologists(this.myPharmacy.id).subscribe({
      next: dermatologist => {
        this.availableDermatologists = dermatologist;
      }
    });
  }

 addDermatologist() {
    this.pharmacyService.addDermatologist(this.myPharmacy.id, this.dermatologistId, this.workDays).subscribe({
      next: dermatologist => {
        this.dermatologistId = dermatologist;
        this.refresh();
        if(this.dermatologistId == null){
          alert("This dermatologist is not available on chosen days!");
        }
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
        this.getMyDermas();
        this.getAvailableDermatologists();
      }
    });

  }

  createAppointment(){
    console.log(this.appointmentDate);
    console.log(this.appointmentTime);
    console.log(this.appointmentDermId);
    console.log(this.appointmentPrice);

    this.pharmacyAdminService.createDermatologistAppointment(this.myPharmacy.id, this.appointmentDermId, this.appointmentTime, this.appointmentPrice, this.appointmentDate).subscribe({
      next: ret => {
        this.ret = ret;
        this.refresh();
        if(this.ret == null){
          alert("Dermatologist is not available. Please chose another day/time!");
        }
        else
        alert("Appointment booked successfully! ");
      }
    });

  }

}
