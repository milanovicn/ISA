import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Login } from '../../shared/model/Login';
import { LoginService } from '../../shared/service/login.service';
import { User } from '../../shared/model/User';

@Component({
  selector: 'registration-notice',
  templateUrl: './registration-notice.component.html'
})

export class RegistrationNoticeComponent{
  user: User;

  constructor(private router: Router, private loginService: LoginService) {
  }

  getUser() {

    this.loginService.getLoggedInUser().subscribe({
      next: user => {
        this.user = user;

        console.log(this.user);

        if (this.user.userRole == "PHARMACY_ADMIN") {

          this.router.navigate(["/pharmacy-admin-homepage"]);

        } else if (this.user.userRole == "SYSTEM_ADMIN") {

          this.router.navigate(["/system-admin-homepage"]);

        }else if (this.user.userRole == "SUPPLIER") {

          this.router.navigate(["/supplier-homepage"]);

        }else if (this.user.userRole == "PHARMACIST") {

          this.router.navigate(["/pharmacist-homepage"]);

        }else if (this.user.userRole == "DERMATOLOGIST") {

          this.router.navigate(["/dermatologist-homepage"]);

        }


      }

    });




  }

  }