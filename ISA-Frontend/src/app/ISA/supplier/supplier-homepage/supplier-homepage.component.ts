
import { Component, OnInit } from '@angular/core';
import { Routes, Router } from '@angular/router';
import { LoginService } from 'app/ISA/shared/service/login.service';
import { User } from 'app/ISA/shared/model/User';



@Component({
  templateUrl: './supplier-homepage.component.html'

})

export class SupplierHomepageComponent implements OnInit {
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
        if (user.prviPutLogovan == true) {
          console.log("usao");
          this.router.navigate(["/homepage/change-password"]);
        }
      }

    });

  }

  redirect() {
    this.router.navigate(["/homepage"]);
  }

}