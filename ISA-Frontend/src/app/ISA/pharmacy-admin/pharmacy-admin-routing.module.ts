import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { FirstLoginComponent } from './first-login/first-login.component';
import { PharmacyAdminHisPharmacyComponent } from './pharmacy-admin-hispharmacy/pharmacy-admin-hispharmacy.component';
import { PharmacyAdminHomepageComponent } from './pharmacy-admin-homepage/pharmacy-admin-homepage.component';
import { PharmacyAdminProfileComponent } from './pharmacy-admin-profile/pharmacy-admin-profile.component';

const routes: Routes = [
  { path: 'pharmacy-admin-homepage', component: PharmacyAdminHomepageComponent },
    {path: 'pharmacy-admin-homepage/changepassword', component : FirstLoginComponent },
    {path: 'pharmacy-admin-homepage/pharmacy-admin-profile', component : PharmacyAdminProfileComponent },
    {path: 'pharmacy-admin-homepage/pharmacy-admin-hispharmacy', component : PharmacyAdminHisPharmacyComponent },
  ];


@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class PharmacyAdminRoutingModule { }