import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { PharmacistRoutingModule } from './pharmacist-routing.module';
import { PharmacistNavbarComponent } from './pharmacist-navbar/pharmacist-navbar.component';
import { PharmacistProfileComponent } from './pharmacist-profile/pharmacist-profile.component';
import { MatSelectModule } from '@angular/material/select';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatNativeDateModule } from '@angular/material/core';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MedicineReservationComponent } from './medicine-reservation/medicine-reservation.component';
import { PharmacistAppointmentComponent } from './pharmacist-appointment/pharmacist-appointment.component';
import { PharmacistAppointmentsComponent } from './pharmacist-appointments/pharmacist-appointments.component';
import { PatientsFarmComponent } from './patients/patients.component';



@NgModule({
  declarations: 
  [PharmacistNavbarComponent, 
    PharmacistProfileComponent, 
    MedicineReservationComponent,
    PharmacistAppointmentComponent,
    PharmacistAppointmentsComponent,
    PatientsFarmComponent
  ],
  imports: [
    CommonModule,
    PharmacistRoutingModule, 
    RouterModule,
    FormsModule,
    MatSelectModule,
    MatDatepickerModule,
    MatNativeDateModule,
    MatFormFieldModule,
    MatInputModule
  ]
})
export class PharmacistModule { }
