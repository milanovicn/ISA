import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { MedicineReservationComponent } from './medicine-reservation/medicine-reservation.component';
import { PatientsFarmComponent } from './patients/patients.component';
import { PharmacistAppointmentComponent } from './pharmacist-appointment/pharmacist-appointment.component';
import { PharmacistAppointmentsComponent } from './pharmacist-appointments/pharmacist-appointments.component';
import { PharmacistHomepageComponent } from './pharmacist-homepage/pharmacist-homepage.component';
import { PharmacistProfileComponent } from './pharmacist-profile/pharmacist-profile.component';
import { PharmacistReportsComponent } from './pharmacist-reports/pharmacist-reports.components';



const routes: Routes = [
  { path: 'pharmacist-homepage', component: PharmacistHomepageComponent },
  { path: 'pharmacist-homepage/pharmacist-profile', component: PharmacistProfileComponent },
  { path: 'pharmacist-homepage/medicine-reservation', component: MedicineReservationComponent},
  { path: 'pharmacist-homepage/pharmacist-appointments', component: PharmacistAppointmentsComponent},
  { path: 'pharmacist-homepage/pharmacist-appointments/:id', component: PharmacistAppointmentComponent},
  { path: 'pharmacist-homepage/patients', component: PatientsFarmComponent},
  { path: 'pharmacist-homepage/pharmacist-reports', component: PharmacistReportsComponent}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class PharmacistRoutingModule { }
