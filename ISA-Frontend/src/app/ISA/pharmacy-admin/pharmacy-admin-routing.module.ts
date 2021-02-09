import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { DermasComponent } from './dermas-pharmacy/dermas-pharmacy.component';
import { FirstLoginComponent } from './first-login/first-login.component';
import { OffersComponent } from './offers/offers.component';
import { OrdersComponent } from './orders/orders.component';
import { PharmacyAdminHisPharmacyComponent } from './pharmacy-admin-hispharmacy/pharmacy-admin-hispharmacy.component';
import { PharmacyAdminHomepageComponent } from './pharmacy-admin-homepage/pharmacy-admin-homepage.component';
import { PharmacyAdminProfileComponent } from './pharmacy-admin-profile/pharmacy-admin-profile.component';
import { PharmacyMedicineComponent } from './pharmacy-medicine/pharmacy-medicine.component';
import { PharmasComponent } from './pharmas-pharmacy/pharmas-pharmacy.component';

const routes: Routes = [
  { path: 'pharmacy-admin-homepage', component: PharmacyAdminHomepageComponent },
    {path: 'pharmacy-admin-homepage/changepassword', component : FirstLoginComponent },
    {path: 'pharmacy-admin-homepage/pharmacy-admin-profile', component : PharmacyAdminProfileComponent },
    {path: 'pharmacy-admin-homepage/pharmacy-admin-hispharmacy', component : PharmacyAdminHisPharmacyComponent },
    {path: 'pharmacy-admin-homepage/dermas-pharmacy', component : DermasComponent },  
    {path: 'pharmacy-admin-homepage/pharmas-pharmacy', component : PharmasComponent },  
    {path: 'pharmacy-admin-homepage/pharmacy-medicine', component : PharmacyMedicineComponent },  
    {path: 'pharmacy-admin-homepage/pharmacy-admin-orders', component: OrdersComponent },
  { path: 'pharmacy-admin-homepage/pharmacy-admin-orders/:id', component: OffersComponent },
  ];


@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class PharmacyAdminRoutingModule { }