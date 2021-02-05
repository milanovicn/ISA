import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { SupplierRoutingModule } from './supplier-routing.module';
import { SupplierNavbarComponent } from './supplier-navbar/supplier-navbar.component';
import { SupplierProfileComponent } from './supplier-profile/supplier-profile.component';
import { SupplierOrdersComponent } from './supplier-orders/supplier-orders.component';
import { SupplierOrderComponent } from './supplier-order/supplier-order.component';
import { MatSelectModule } from '@angular/material/select';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatNativeDateModule } from '@angular/material/core';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { SupplierOffersComponent } from './supplier-offers/supplier-offers.component';


@NgModule({
  declarations: [
    SupplierNavbarComponent, 
    SupplierProfileComponent,
    SupplierOrdersComponent,
    SupplierOrderComponent,
    SupplierOffersComponent
  ],
  imports: [
    CommonModule,
    SupplierRoutingModule, 
    RouterModule,
    FormsModule,
    MatSelectModule,
    MatDatepickerModule,
    MatNativeDateModule,
    MatFormFieldModule,
    MatInputModule,
    
  ]
})
export class SupplierModule { }
