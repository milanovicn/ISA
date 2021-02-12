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
import { MatSelectModule } from '@angular/material/select';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatNativeDateModule } from '@angular/material/core';
import { MatInputModule } from '@angular/material/input';
import { MatFormFieldModule } from '@angular/material/form-field';
import { DermatologistReportsComponent } from './dermatologist-reports/dermatologist-reports.component';
import { DermatologistReportComponent } from './dermatologist-report/dermatologist-report.component';




@NgModule({
  declarations: [DermatologistNavbarComponent, DermatologistProfileComponent, DermatologistAppointmentsComponent,
     PatientsDermComponent, DermatologistAppointmentComponent, DermatologistReportsComponent, DermatologistReportComponent],
  imports: [
    CommonModule,
    DermatologistRoutingModule, 
    RouterModule,
    FormsModule,
    MatSelectModule,
    MatDatepickerModule,
    MatNativeDateModule,
    MatFormFieldModule,
    MatInputModule
  ]
})
export class DermatologistModule { }
