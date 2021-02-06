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
import { asLiteral } from '@angular/compiler/src/render3/view/util';

@Component({
  selector: 'pharmas-pharmacy',
  templateUrl: './pharmas-pharmacy.component.html',
})
export class PharmasComponent implements OnInit {
  user: User;
  myPharmacy: Pharmacy;
  myDermas: Dermatologist[] = [];
  myPharmas: Pharmacist[] = [];
  searchParameters: SearchPharmacist;
  myMedicine:Medicine[];
  newPharma : User;
  workDays: string[] = [];
  pharmacistId:number = 0;
  pharmacistIdDelete:number=0;
  appointmentPharmId:number=0;
  appointmentTime:string = "";
  appointmentDate:Date=new Date(); 
  appointmentPrice:number=0;
  ret: Object;

  constructor(private _router: Router, private pharmacyService: PharmacyService, 
    private pharmacyAdminService: PharmacyAdminService, 
    private loginService: LoginService, private medicineService: MedicineService) {
    this.user = new User();
    this.myPharmacy = new Pharmacy();
    this.myDermas = [];
    this.myPharmas=[];
    this.searchParameters = new SearchPharmacist();
    this.newPharma = new User();
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
    if(this.searchParameters.address == undefined && this.searchParameters.city == undefined
      && this.searchParameters.email == undefined && this.searchParameters.firstname == undefined
      && this.searchParameters.lastname  == undefined && this.searchParameters.rateFrom == undefined &&  this.searchParameters.rateTo== undefined)
       {
        alert("You did not enter any parameter!");
        this.refresh();
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
  // getAllMedicine() {
  //   this.pharmacyService.getMedicine(this.myPharmacy.id).subscribe({
  //     next:  medicine => {
  //       this.myMedicine = medicine;
  //     }
  //   });
  // }

  deletePharma(): void {
    this.pharmacyService.deletePharma(this.pharmacistIdDelete).subscribe();
    this.refresh();
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
        
        //this.getAllMedicine();
        
        
      }
    });

  }

  
  registerPharma() {
    console.log(this.newPharma);
    this.pharmacyAdminService.registerPharma(this.newPharma, this.myPharmacy.id).subscribe(
        {
            next: newPh => {
                this.newPharma = newPh;
                console.log(this.newPharma);
                this.refresh();
                if (this.newPharma == null) {
                    alert("Pharmacist with this email is already registered!");
                }
                else
                alert("Pharmacist registered!");
            }
        });
   
   
}
addPharmacist() {
  this.pharmacyService.addPharmacist(this.myPharmacy.id, this.pharmacistId, this.workDays).subscribe({
    next: pharmacist => {
      this.pharmacistId = pharmacist;
     
      this.refresh();
      if(this.pharmacistId == null){
        alert("You have already assigned working days for this pharmacist. Please chose another one!");
      }
      else
      alert("Working days assigned successfully! ");
    }
  });
}

createAppointment(){
  console.log(this.appointmentDate);
  console.log(this.appointmentTime);
  console.log(this.appointmentPharmId);
  console.log(this.appointmentPrice);

  this.pharmacyAdminService.createPharmacistAppointment(this.myPharmacy.id, this.appointmentPharmId, this.appointmentTime, this.appointmentPrice, this.appointmentDate).subscribe({
    next: ret => {
      this.ret = ret;
      console.log(ret);
      this.refresh();
      
      if(this.ret == null){
        alert("Pharmacist is not available. Please chose another day/time!");
      }
      else
      alert("Consultation booked successfully! ");
     
    }
    
  });

}

}
