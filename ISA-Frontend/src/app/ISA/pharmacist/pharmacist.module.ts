import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { PharmacistRoutingModule } from './pharmacist-routing.module';
import { PharmacistNavbarComponent } from './pharmacist-navbar/pharmacist-navbar.component';
import { PharmacistProfileComponent } from './pharmacist-profile/pharmacist-profile.component';
import { PharmacistSchedulingCounseling } from './pharmacist-schedulingCounseling/pharmacist-schedulingCounseling.component';
import { MatSelectModule } from '@angular/material/select';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatNativeDateModule } from '@angular/material/core';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';


@NgModule({
  declarations: [PharmacistNavbarComponent, PharmacistProfileComponent, PharmacistSchedulingCounseling,
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
