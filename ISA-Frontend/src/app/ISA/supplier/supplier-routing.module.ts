import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { SupplierHomepageComponent } from './supplier-homepage/supplier-homepage.component';
import { SupplierOffersComponent } from './supplier-offers/supplier-offers.component';
import { SupplierOrderComponent } from './supplier-order/supplier-order.component';
import { SupplierOrdersComponent } from './supplier-orders/supplier-orders.component';
import { SupplierProfileComponent } from './supplier-profile/supplier-profile.component';



const routes: Routes = [
  { path: 'supplier-homepage', component: SupplierHomepageComponent },
  { path: 'supplier-homepage/supplier-profile', component: SupplierProfileComponent },
  { path: 'supplier-homepage/supplier-orders', component: SupplierOrdersComponent },
  { path: 'supplier-homepage/supplier-orders/:id', component: SupplierOrderComponent },
  { path: 'supplier-homepage/supplier-offers', component: SupplierOffersComponent },
  //{ path: 'user-homepage/user-pharmacies', component: UserPharmaciesComponent },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class SupplierRoutingModule { }
