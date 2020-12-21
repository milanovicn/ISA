
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Login } from '../../shared/model/Login';
import { LoginService } from '../../shared/service/login.service';
import { User } from '../../shared/model/User';
import { PharmacyAdminService } from 'app/ISA/shared/service/pharmacy-admin.service';

@Component({
  selector: 'first-login',
  templateUrl: './first-login.component.html'
})

export class FirstLoginComponent implements OnInit {
  user: User;
  response: Response;
  respBool: boolean = false;
  newPassword: string= "";

  constructor(private router: Router,private pharmacyadminservice:PharmacyAdminService, private loginService: LoginService) {
    
    this.user = new User();

  }

  ngOnInit() {
    this.getUser();
  }


  change_password(){
    this.pharmacyadminservice.update(this.user.id,this.newPassword).subscribe({
        next: user => {
          this.user = user;
          console.log(this.user);
          this.router.navigate(["/pharmacy-admin-homepage"]);
  
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
