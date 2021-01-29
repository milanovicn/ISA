import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AppModule } from 'app/app.module';
import { LoginService } from 'app/ISA/shared/service/login.service';
import { User } from 'app/ISA/shared/model/User';
import { PharmacistService } from 'app/ISA/shared/service/pharmacist.service';
import { DermatologistService } from 'app/ISA/shared/service/dermatologist.service';

@Component({
    selector: 'dermatologist-profile',
    templateUrl: './dermatologist-profile.component.html',
  })
  export class DermatologistProfileComponent implements OnInit {
    user: User;
    updatedUser:User;
  
    constructor(private _router: Router, private dermatologistService: DermatologistService, private loginService: LoginService) {
      this.user = new User();
      this.updatedUser = new User();
     
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
  
    edit() {
      console.log("usr");
      console.log(this.user);
      this.dermatologistService.update(this.user).subscribe();
      this.refresh();
    }
    
   
  
    refresh(){
      window.location.reload();
    }
  }
  