import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { SupplierRoutingModule } from './supplier-routing.module';
import { SupplierNavbarComponent } from './supplier-navbar/supplier-navbar.component';
import { SupplierProfileComponent } from './supplier-profile/supplier-profile.component';


@NgModule({
  declarations: [
    SupplierNavbarComponent, 
    SupplierProfileComponent,
  ],
  imports: [
    CommonModule,
    SupplierRoutingModule, 
    RouterModule,
    FormsModule
  ]
})
export class SupplierModule { }
