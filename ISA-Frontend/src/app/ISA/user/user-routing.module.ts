import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { DermatologistAppointmentsComponent } from './dermatologist-appointments/dermatologist-appointments.component';
import { UserMedicinesComponent } from './medicines/medicines.component';
import { PharmacistAppointmentsComponent } from './pharmacist-appointment/pharmacist-appointment.component';
import { UserHomepageComponent } from './user-homepage/user-homepage.component';
import { UserPharmaciesComponent } from './user-pharmacies/user-pharmacies.component';
import { UserPharmacyComponent } from './user-pharmacy/user-pharmacy.component';
import { UserProfileComponent } from './user-profile/user-profile.component';

const routes: Routes = [
 
  { path: 'user-homepage', component: UserHomepageComponent },
  { path: 'user-homepage/user-profile', component: UserProfileComponent },
  { path: 'user-homepage/user-pharmacies', component: UserPharmaciesComponent },
  { path: 'user-homepage/user-pharmacies/:id', component: UserPharmacyComponent },
  { path: 'user-homepage/dermatologist-appointments', component: DermatologistAppointmentsComponent },
  { path: 'user-homepage/pharmacist-appointments', component: PharmacistAppointmentsComponent },
  { path: 'user-homepage/user-medicines', component: UserMedicinesComponent },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class UserRoutingModule { }
