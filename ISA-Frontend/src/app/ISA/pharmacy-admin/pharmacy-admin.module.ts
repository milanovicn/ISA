import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { RouterModule } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { PharmacyAdminRoutingModule } from './pharmacy-admin-routing.module';


@NgModule({
  declarations: [ ],
  imports: [
    CommonModule,
    PharmacyAdminRoutingModule, 
    RouterModule,
    FormsModule
  ]
})
export class PharmacyAdminModule { }
