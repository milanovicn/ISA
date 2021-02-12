import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { DermatologistHomepageComponent } from './dermatologist-homepage/dermatologist-homepage.component';
import { DermatologistProfileComponent } from './dermatologist-profile/dermatologist-profile.component';
import { PatientsDermComponent } from './patients/patients.component';
import { DermatologistAppointmentsComponent } from './dermatologist-appointments/dermatologist-appointments.component';
import { DermatologistAppointmentComponent } from './dermatologist-appointment/dermatologist-appointment.component';
import { DermatologistReportsComponent } from './dermatologist-reports/dermatologist-reports.component';
import { DermatologistReportComponent } from './dermatologist-report/dermatologist-report.component';



const routes: Routes = [
  { path: 'dermatologist-homepage', component: DermatologistHomepageComponent },
  { path: 'dermatologist-homepage/dermatologist-profile', component: DermatologistProfileComponent },
  { path: 'dermatologist-homepage/patients', component: PatientsDermComponent},
  { path: 'dermatologist-homepage/dermatologist-appointments', component: DermatologistAppointmentsComponent},
  { path: 'dermatologist-homepage/dermatologist-appointments/:id', component: DermatologistAppointmentComponent},
  { path: 'dermatologist-homepage/dermatologist-reports', component: DermatologistReportsComponent},
  { path: 'dermatologist-homepage/dermatologist-reports/:id', component: DermatologistReportComponent}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class DermatologistRoutingModule { }
