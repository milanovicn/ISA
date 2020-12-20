import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { PharmacyAdminHomepageComponent } from './pharmacy-admin-homepage/pharmacy-admin-homepage.component';

const routes: Routes = [
  { path: 'pharmacy-admin-homepage', component: PharmacyAdminHomepageComponent },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class PharmacyAdminRoutingModule { }