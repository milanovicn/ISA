import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { RouterModule } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { PharmacyAdminRoutingModule } from './pharmacy-admin-routing.module';
import { PharmacyAdminNavbarComponent } from './pharmacy-admin-navbar/pharmacy-admin-navbar.component';
import { PharmacyAdminProfileComponent } from './pharmacy-admin-profile/pharmacy-admin-profile.component';
import { FirstLoginComponent } from './first-login/first-login.component';
import { PharmacyAdminHisPharmacyComponent } from './pharmacy-admin-hispharmacy/pharmacy-admin-hispharmacy.component';
import { PharmasComponent } from './pharmas-pharmacy/pharmas-pharmacy.component';
import { DermasComponent } from './dermas-pharmacy/dermas-pharmacy.component';
import { PharmacyMedicineComponent } from './pharmacy-medicine/pharmacy-medicine.component';
import { MatSelectModule } from '@angular/material/select';
import {MatDatepickerModule} from '@angular/material/datepicker';
import { MatNativeDateModule } from '@angular/material/core';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';


@NgModule({
  declarations: [ 
    PharmacyAdminNavbarComponent,
    PharmacyAdminHisPharmacyComponent,
    PharmacyAdminProfileComponent,
    FirstLoginComponent,
    DermasComponent,
    PharmasComponent,
    PharmacyMedicineComponent,
  ],
  imports: [
    CommonModule,
    PharmacyAdminRoutingModule, 
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
export class PharmacyAdminModule { }
