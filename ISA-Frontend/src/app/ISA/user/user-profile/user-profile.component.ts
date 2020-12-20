import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AppModule } from 'app/app.module';
import { LoginService } from 'app/ISA/shared/service/login.service';
import { User } from 'app/ISA/shared/model/User';
import { Medicine } from 'app/ISA/shared/model/Medicine';
import { MedicineService } from 'app/ISA/shared/service/medicine.service';
import { UserService } from '../../shared/service/user.service';

@Component({
  selector: 'app-user-profile',
  templateUrl: './user-profile.component.html',
})
export class UserProfileComponent implements OnInit {
  user: User;

  updatedUser:User;
  myAllergies: Medicine[] = [];
  allMedications: Medicine[] = [];
  newAllergy: number;

  constructor(private _router: Router, private userService: UserService, private loginService: LoginService, private medicineService: MedicineService) {
    this.user = new User();
    this.updatedUser = new User();
    this.myAllergies = [];
    this.allMedications = [];
    this.newAllergy = 0;
  }

  ngOnInit(): void {
    this.getUser();

    
    console.log(this.user);
  }

  getMyAllergies() {
    this.userService.getAllergies(this.user.id).subscribe({
           next: allergies => {
             this.myAllergies = allergies;
           }
       });
  }

  getAllMedicines() {
    this.medicineService.getAllMedicines().subscribe({
           next: medicines => {
             this.allMedications = medicines;
           }
       });
  }

  getUser() {

    this.loginService.getLoggedInUser().subscribe({
      next: t => {
        this.user = t;

        console.log(this.user);
        this.getMyAllergies();
        this.getAllMedicines();
      }

    });

  }

  edit() {
    console.log("usr");
    console.log(this.user);
    this.userService.update(this.user).subscribe();
    this.refresh();
  }
  
  addAllergy(){
    
    console.log(this.newAllergy);
    
    this.userService.addAllergy(this.user, this.newAllergy).subscribe({
      next: user => {
        this.user = user;

        console.log(this.user);
        this.refresh();
      }
    });
  }

  refresh(){
    window.location.reload();
  }
}
