import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { UserRoutingModule } from './user-routing.module';
import { UserProfileComponent } from './user-profile/user-profile.component';
import { RouterModule } from '@angular/router';
import { UserNavbarComponent } from './user-navbar/user-navbar.component';
import { FormsModule } from '@angular/forms';
import { UserPharmaciesComponent } from './user-pharmacies/user-pharmacies.component';
import { UserPharmacyComponent } from './user-pharmacy/user-pharmacy.component';
import { MatSelectModule } from '@angular/material/select';
import {MatDatepickerModule} from '@angular/material/datepicker';
import { MatNativeDateModule } from '@angular/material/core';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { DermatologistAppointmentsComponent } from './dermatologist-appointments/dermatologist-appointments.component';
import { PharmacistAppointmentsComponent } from './pharmacist-appointment/pharmacist-appointment.component';
import { UserMedicinesComponent } from './medicines/medicines.component';
import { ComplaintComponent } from './complaint/complaint.component';


@NgModule({
  declarations: [
    UserProfileComponent, 
    UserNavbarComponent, 
    UserPharmaciesComponent,
    UserPharmacyComponent,
    DermatologistAppointmentsComponent,
    PharmacistAppointmentsComponent,
    UserMedicinesComponent,
    ComplaintComponent
   ],
  imports: [
    CommonModule,
    UserRoutingModule, 
    RouterModule,
    FormsModule,
    MatSelectModule,
    MatDatepickerModule,
    MatNativeDateModule,
    MatFormFieldModule,
    MatInputModule
  ],
  providers: [  
  MatDatepickerModule,  
],
})
export class UserModule { }
