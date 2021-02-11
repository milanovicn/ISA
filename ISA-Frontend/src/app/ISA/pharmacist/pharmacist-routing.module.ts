import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { MedicineReservationComponent } from './medicine-reservation/medicine-reservation.component';
import { PharmacistHomepageComponent } from './pharmacist-homepage/pharmacist-homepage.component';
import { PharmacistProfileComponent } from './pharmacist-profile/pharmacist-profile.component';
import { PharmacistSchedulingCounseling } from './pharmacist-schedulingCounseling/pharmacist-schedulingCounseling.component';




const routes: Routes = [
  { path: 'pharmacist-homepage', component: PharmacistHomepageComponent },
  { path: 'pharmacist-homepage/pharmacist-profile', component: PharmacistProfileComponent },
  { path: 'pharmacist-homepage/pharmacist-schedulingCounseling', component: PharmacistSchedulingCounseling},
  { path: 'pharmacist-homepage/medicine-reservation', component: MedicineReservationComponent},
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class PharmacistRoutingModule { }
