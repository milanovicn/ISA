import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { DermatologistHomepageComponent } from './dermatologist-homepage/dermatologist-homepage.component';
import { DermatologistMakingAppointmentComponent } from './dermatologist-makingAppointment/dermatologist-makingAppointment.component';
import { DermatologistProfileComponent } from './dermatologist-profile/dermatologist-profile.component';
import { PatientsDermComponent } from './patients/patients.component';




const routes: Routes = [
  { path: 'dermatologist-homepage', component: DermatologistHomepageComponent },
  { path: 'dermatologist-homepage/dermatologist-profile', component: DermatologistProfileComponent },
  {path: 'dermatologist-homepage/dermatologist-makingAppointment', component: DermatologistMakingAppointmentComponent},
  {path: 'dermatologist-homepage/patients', component: PatientsDermComponent}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class DermatologistRoutingModule { }
