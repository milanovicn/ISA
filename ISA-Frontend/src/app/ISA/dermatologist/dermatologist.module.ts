import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { DermatologistRoutingModule } from './dermatologist-routing.module';
import { DermatologistNavbarComponent } from './dermatologist-navbar/dermatologist.navbar.component';
import { DermatologistProfileComponent } from './dermatologist-profile/dermatologist-profile.component';
import { PatientsDermComponent } from './patients/patients.component';
import { DermatologistAppointmentsComponent } from './dermatologist-appointments/dermatologist-appointments.component';
import { DermatologistAppointmentComponent } from './dermatologist-appointment/dermatologist-appointment.component';




@NgModule({
  declarations: [DermatologistNavbarComponent, DermatologistProfileComponent, DermatologistAppointmentsComponent,
     PatientsDermComponent, DermatologistAppointmentComponent],
  imports: [
    CommonModule,
    DermatologistRoutingModule, 
    RouterModule,
    FormsModule,
  ]
})
export class DermatologistModule { }
