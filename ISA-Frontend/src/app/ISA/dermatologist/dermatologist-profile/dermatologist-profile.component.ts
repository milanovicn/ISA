import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AppModule } from 'app/app.module';
import { LoginService } from 'app/ISA/shared/service/login.service';
import { User } from 'app/ISA/shared/model/User';
import { PharmacistService } from 'app/ISA/shared/service/pharmacist.service';
import { DermatologistService } from 'app/ISA/shared/service/dermatologist.service';
import { DermatologistVacation } from 'app/ISA/shared/model/DermatologistVacation';

@Component({
    selector: 'dermatologist-profile',
    templateUrl: './dermatologist-profile.component.html',
  })
  export class DermatologistProfileComponent implements OnInit {
    user: User;
    updatedUser:User;
    myVacations: DermatologistVacation[] = [];
    newVacation : DermatologistVacation;
    dateFromA:Date=new Date(); 
    dateToA:Date=new Date();
  
    constructor(private _router: Router, private dermatologistService: DermatologistService, private loginService: LoginService) {
      this.user = new User();
      this.updatedUser = new User();
      this.myVacations = [];
      this.newVacation = new DermatologistVacation();
     
    }
  
    ngOnInit(): void {
      this.getUser();
  
      
      console.log(this.user);
    }

    getUser() {
  
      this.loginService.getLoggedInUser().subscribe({
        next: t => {
          this.user = t;
          this.getAllVacations();
          console.log(this.user);
        }
  
      });
  
    }

    
    getAllVacations() {
      this.dermatologistService.getMyVacations(this.user.id).subscribe({
        next: actionss => {
          this.myVacations = actionss;
        }
      });
    }
  
    edit() {
      console.log("usr");
      console.log(this.user);
      this.dermatologistService.update(this.user).subscribe();
      this.refresh();
    }
    
    addVacation(){
      console.log(this.newVacation);
      this.dermatologistService.addVacation(this.newVacation,this.user.id).subscribe({
        next: ret => {
          this.newVacation = ret;
          console.log(this.newVacation);
          this.refresh();
          
        
        }
        
      });
    
    }
  
    refresh(){
      window.location.reload();
    }
  }
  