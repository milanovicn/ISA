import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { SystemAdminRoutingModule } from './system-admin-routing.module';
import { SystemAdminNavbarComponent } from './system-admin-navbar/system-admin-navbar.component';
import { SuppliersComponent } from './suppliers/suppliers.component';
import { MedicinesComponent } from './medicines/medicines.component';
import { MatSelectModule } from '@angular/material/select';
import { NewSystemAdminComponent } from './new-system-admin/new-system-admin.component';
import { NewPharmacyComponent } from './new-pharmacy/new-pharmacy.component';
import { NewPharmacyAdminComponent } from './new-pharmacy-admin/new-pharmacy-admin.component';
import {MatDatepickerModule} from '@angular/material/datepicker';

@NgModule({
  declarations: [
    SystemAdminNavbarComponent, 
    SuppliersComponent,
    MedicinesComponent,
    NewSystemAdminComponent,
    NewPharmacyComponent,
    NewPharmacyAdminComponent,
  ],
  imports: [
    CommonModule,
    SystemAdminRoutingModule, 
    RouterModule,
    FormsModule,
    MatSelectModule,
    MatDatepickerModule
  ]
})
export class SystemAdminModule { }
