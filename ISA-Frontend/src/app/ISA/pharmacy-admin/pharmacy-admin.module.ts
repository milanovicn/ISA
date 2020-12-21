import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { RouterModule } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { PharmacyAdminRoutingModule } from './pharmacy-admin-routing.module';
import { PharmacyAdminNavbarComponent } from './pharmacy-admin-navbar/pharmacy-admin-navbar.component';
import { PharmacyAdminProfileComponent } from './pharmacy-admin-profile/pharmacy-admin-profile.component';
import { FirstLoginComponent } from './first-login/first-login.component';


@NgModule({
  declarations: [ 
    PharmacyAdminNavbarComponent,
    PharmacyAdminProfileComponent,
    FirstLoginComponent
  ],
  imports: [
    CommonModule,
    PharmacyAdminRoutingModule, 
    RouterModule,
    FormsModule
  
  ]
})
export class PharmacyAdminModule { }
