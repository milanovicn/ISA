import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { DermatologistHomepageComponent } from './dermatologist-homepage/dermatologist-homepage.component';
import { DermatologistProfileComponent } from './dermatologist-profile/dermatologist-profile.component';




const routes: Routes = [
  { path: 'dermatologist-homepage', component: DermatologistHomepageComponent },
  { path: 'dermatologist-homepage/dermatologist-profile', component: DermatologistProfileComponent },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class DermatologistRoutingModule { }
