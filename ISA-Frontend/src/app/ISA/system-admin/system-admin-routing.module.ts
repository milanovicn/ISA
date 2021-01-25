import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { SystemAdminHomepageComponent } from './system-admin-homepage/system-admin-homepage.component';


const routes: Routes = [
 
  { path: 'system-admin-homepage', component: SystemAdminHomepageComponent },
  //{ path: 'user-homepage/user-profile', component: UserProfileComponent },
  //{ path: 'user-homepage/user-pharmacies', component: UserPharmaciesComponent },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class SystemAdminRoutingModule { }
