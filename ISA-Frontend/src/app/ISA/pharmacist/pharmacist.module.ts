import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { PharmacistRoutingModule } from './pharmacist-routing.module';


@NgModule({
  declarations: [
  ],
  imports: [
    CommonModule,
    PharmacistRoutingModule, 
    RouterModule,
    FormsModule
  ]
})
export class PharmacistModule { }
