
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Login } from '../../shared/model/Login';
import { LoginService } from '../../shared/service/login.service';
import { User } from '../../shared/model/User';
import { PharmacyAdminService } from 'app/ISA/shared/service/pharmacy-admin.service';

@Component({
  selector: 'change-password',
  templateUrl: './change-password.component.html'
})

export class ChangePasswordComponent implements OnInit {
  user: User;
  response: Response;
  respBool: boolean = false;
  newPassword: string= "";

  constructor(private router: Router, private loginService: LoginService) {
    
    this.user = new User();

  }

  ngOnInit() {
    this.getUser();
  }


  change_password(){
    this.loginService.changePassword(this.user.id, this.newPassword, this.user.userRole).subscribe({
        next: user => {
          this.user = user;
          console.log(this.user);
          if (this.user.userRole == "PATIENT") {

            this.router.navigate(["/user-homepage"]);
  
          } else if (this.user.userRole == "PHARMACY_ADMIN") {
  
            this.router.navigate(["/pharmacy-admin-homepage"]);
  
          } else if (this.user.userRole == "SYSTEM_ADMIN") {
  
            this.router.navigate(["/system-admin-homepage"]);
  
          } else if (this.user.userRole == "SUPPLIER") {
  
            this.router.navigate(["/supplier-homepage"]);
  
          }else if (this.user.userRole == "PHARMACIST") {

            this.router.navigate(["/pharmacist-homepage"]);
  
          }else if (this.user.userRole == "DERMATOLOGIST") {
  
            this.router.navigate(["/dermatologist-homepage"]);
  
          }
  
        }
  
      });

  }


  getUser() {

    this.loginService.getLoggedInUser().subscribe({
      next: user => {
        this.user = user;

        console.log(this.user);
        

      }

    });

  }

}
