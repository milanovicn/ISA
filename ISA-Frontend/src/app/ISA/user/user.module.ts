import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { UserRoutingModule } from './user-routing.module';
import { UserProfileComponent } from './user-profile/user-profile.component';
import { RouterModule } from '@angular/router';
import { UserNavbarComponent } from './user-navbar/user-navbar.component';
import { FormsModule } from '@angular/forms';
import { UserPharmaciesComponent } from './user-pharmacies/user-pharmacies.component';


@NgModule({
  declarations: [UserProfileComponent, UserNavbarComponent, UserPharmaciesComponent ],
  imports: [
    CommonModule,
    UserRoutingModule, 
    RouterModule,
    FormsModule
  ]
})
export class UserModule { }
