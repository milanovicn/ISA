
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Login } from '../../shared/model/Login';
import { LoginService } from '../../shared/service/login.service';
import { User } from '../../shared/model/User';

@Component({
  selector: 'login',
  templateUrl: './login.component.html'
})

export class LoginComponent implements OnInit {
  user: User;
  response: Response;
  respBool: boolean = false;
  loginRequest: Login;
  request: Request;

  constructor(private router: Router, private loginService: LoginService) {
    this.loginRequest = new Login();
    this.user = new User();

  }

  ngOnInit() {
    this.getUser();
  }

  login() {
    this.respBool = false;
    this.loginService.login(this.loginRequest).subscribe(result => this.getUser(),
      err => this.alertError()

    );
    

  }

  alertError() {
      alert("Wrong password and/or e-mail. Please, try again.");
  }

  getUser() {

    this.loginService.getLoggedInUser().subscribe({
      next: user => {
        this.user = user;

        console.log(this.user);

        if (this.user.userRole == "PATIENT") {

          this.router.navigate(["/user-homepage"]);

        } else if (this.user.userRole == "PHARMACY_ADMIN") {


          this.router.navigate(["/pharmacy-admin-homepage"]);

        }

      }

    });




  }

}
