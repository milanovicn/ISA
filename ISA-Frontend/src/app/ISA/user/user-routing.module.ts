import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { UserHomepageComponent } from './user-homepage/user-homepage.component';
import { UserPharmaciesComponent } from './user-pharmacies/user-pharmacies.component';
import { UserPharmacyComponent } from './user-pharmacy/user-pharmacy.component';
import { UserProfileComponent } from './user-profile/user-profile.component';

const routes: Routes = [
 
  { path: 'user-homepage', component: UserHomepageComponent },
  { path: 'user-homepage/user-profile', component: UserProfileComponent },
  { path: 'user-homepage/user-pharmacies', component: UserPharmaciesComponent },
  { path: 'user-homepage/user-pharmacies/:id', component: UserPharmacyComponent },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class UserRoutingModule { }
