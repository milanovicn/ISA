import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { DermatologistHomepageComponent } from './dermatologist-homepage/dermatologist-homepage.component';
import { DermatologistMakingAppointmentComponent } from './dermatologist-makingAppointment/dermatologist-makingAppointment.component';
import { DermatologistProfileComponent } from './dermatologist-profile/dermatologist-profile.component';
import { PatientsDermComponent } from './patients/patients.component';
import { DermatologistAppointmentsComponent } from './dermatologist-appointments/dermatologist-appointments.component';
import { DermatologistAppointmentComponent } from './dermatologist-appointment/dermatologist-appointment.component';



const routes: Routes = [
  { path: 'dermatologist-homepage', component: DermatologistHomepageComponent },
  { path: 'dermatologist-homepage/dermatologist-profile', component: DermatologistProfileComponent },
  {path: 'dermatologist-homepage/dermatologist-makingAppointment', component: DermatologistMakingAppointmentComponent},
  {path: 'dermatologist-homepage/patients', component: PatientsDermComponent},
  {path: 'dermatologist-homepage/dermatologist-appointments', component: DermatologistAppointmentsComponent},
  {path: 'dermatologist-homepage/dermatologist-appointments/:id', component: DermatologistAppointmentComponent}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class DermatologistRoutingModule { }
