import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AppModule } from 'app/app.module';
import { LoginService } from 'app/ISA/shared/service/login.service';
import { User } from 'app/ISA/shared/model/User';
import { Medicine } from 'app/ISA/shared/model/Medicine';
import { MedicineService } from 'app/ISA/shared/service/medicine.service';
import { UserService } from '../../shared/service/user.service';
import { PharmacyAdminService } from 'app/ISA/shared/service/pharmacy-admin.service';

@Component({
  selector: 'pharmacy-admin-profile',
  templateUrl: './pharmacy-admin-profile.component.html',
})
export class PharmacyAdminProfileComponent implements OnInit {
  user: User;


  constructor(private _router: Router,private pharmacyAdminService: PharmacyAdminService, private loginService: LoginService, private medicineService: MedicineService) {
    this.user = new User();
  
  }

  ngOnInit(): void {
    this.getUser();

    
    console.log(this.user);
  }



  getUser() {

    this.loginService.getLoggedInUser().subscribe({
      next: t => {
        this.user = t;

        console.log(this.user);
       
      }

    });

  }

  

  refresh(){
    window.location.reload();
  }
}
