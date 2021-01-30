import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { MedicinesComponent } from './medicines/medicines.component';
import { NewPharmacyAdminComponent } from './new-pharmacy-admin/new-pharmacy-admin.component';
import { NewPharmacyComponent } from './new-pharmacy/new-pharmacy.component';
import { NewSystemAdminComponent } from './new-system-admin/new-system-admin.component';
import { SuppliersComponent } from './suppliers/suppliers.component';
import { SystemAdminHomepageComponent } from './system-admin-homepage/system-admin-homepage.component';


const routes: Routes = [
 
  { path: 'system-admin-homepage', component: SystemAdminHomepageComponent },
  { path: 'system-admin-homepage/suppliers', component: SuppliersComponent },
  { path: 'system-admin-homepage/medicines', component: MedicinesComponent },
  { path: 'system-admin-homepage/system-admins', component: NewSystemAdminComponent },
  { path: 'system-admin-homepage/pharmacies', component: NewPharmacyComponent },
  { path: 'system-admin-homepage/pharmacy-admins', component: NewPharmacyAdminComponent },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class SystemAdminRoutingModule { }
