
import { Component, OnInit } from '@angular/core';
import { Routes, Router } from '@angular/router';
import { LoginService } from 'app/ISA/shared/service/login.service';
import { User } from 'app/ISA/shared/model/User';



@Component({
  templateUrl: './pharmacy-admin-homepage.component.html'

})
export class PharmacyAdminHomepageComponent implements OnInit {
  user: User;
  request: Request;

  constructor(private router: Router, private loginService: LoginService) {
    this.user = new User();


  }
  ngOnInit(): void {
    this.getUser();
  }




  logOut() {
    this.loginService.logout(this.request).subscribe(result => this.redirect());
  }

  getUser() {

    this.loginService.getLoggedInUser().subscribe({
      next: user => {
        this.user = user;

      }

    });

  }

  redirect() {
    this.router.navigate(["/homepage"]);
  }

}