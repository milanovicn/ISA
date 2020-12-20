
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
      err => this.respBool = true
     
    );

  }

  getUser() {

    this.loginService.getLoggedInUser().subscribe({
      next: user => {
        this.user = user;

        console.log(this.user);

        if (this.user.userRole == "PATIENT") {
        console.log(this.user);
          this.router.navigate(["/user-homepage"]);

        } else if(this.user.userRole == "PHARMACY_ADMIN"){
          console.log(this.user);
          this.router.navigate(["/pharmacy-admin-homepage"]);
        } 
            /*
             else if(this.korisnik.uloga=="LEKAR"){
               this.router.navigate(["/lekar"]);
           }
             else{
                this.router.navigate(["/signup"]);
             }
            
         */
      }

    });

  }

}
