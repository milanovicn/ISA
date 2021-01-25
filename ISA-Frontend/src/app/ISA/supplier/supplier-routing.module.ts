import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { SupplierHomepageComponent } from './supplier-homepage/supplier-homepage.component';
import { SupplierProfileComponent } from './supplier-profile/supplier-profile.component';



const routes: Routes = [
  { path: 'supplier-homepage', component: SupplierHomepageComponent },
  { path: 'supplier-homepage/supplier-profile', component: SupplierProfileComponent },
  //{ path: 'user-homepage/user-pharmacies', component: UserPharmaciesComponent },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class SupplierRoutingModule { }
