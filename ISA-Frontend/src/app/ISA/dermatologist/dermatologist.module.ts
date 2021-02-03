import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { DermatologistRoutingModule } from './dermatologist-routing.module';
import { DermatologistNavbarComponent } from './dermatologist-navbar/dermatologist.navbar.component';
import { DermatologistProfileComponent } from './dermatologist-profile/dermatologist-profile.component';
import {DermatologistMakingAppointmentComponent } from './dermatologist-makingAppointment/dermatologist-makingAppointment.component';




@NgModule({
  declarations: [DermatologistNavbarComponent, DermatologistProfileComponent, DermatologistMakingAppointmentComponent],
  imports: [
    CommonModule,
    DermatologistRoutingModule, 
    RouterModule,
    FormsModule,
  ]
})
export class DermatologistModule { }
