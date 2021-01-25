
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { LoginService } from '../shared/service/login.service';
import { User } from '../shared/model/User';

@Component({
  selector: 'homepage',
  templateUrl: './homepage.component.html'
})

export class HomePageComponent implements OnInit {
  user: User;

  constructor(private router: Router, private loginService: LoginService) { }
  ngOnInit(): void {
    this.getUser();
  }

  getUser() {

    this.loginService.getLoggedInUser().subscribe({
      next: user => {
        this.user = user;

        console.log(this.user);
        
        console.log(this.user);
        if (this.user.userRole == "PATIENT") {

          console.log(this.user);
          this.router.navigate(["/user-homepage"]);

        } else if (this.user.userRole == "SUPPLIER") {

          console.log(this.user);
          this.router.navigate(["/supplier-homepage"]);

        }else {
          this.router.navigate(["/login"]);
       }
      }

    });

  }

  redirect() {
    this.router.navigate(["/homepage"]);
  }

}
