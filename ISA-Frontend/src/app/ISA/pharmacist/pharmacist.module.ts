import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { PharmacistRoutingModule } from './pharmacist-routing.module';
import { PharmacistNavbarComponent } from './pharmacist-navbar/pharmacist-navbar.component';
import { PharmacistProfileComponent } from './pharmacist-profile/pharmacist-profile.component';


@NgModule({
  declarations: [PharmacistNavbarComponent, PharmacistProfileComponent, 
  ],
  imports: [
    CommonModule,
    PharmacistRoutingModule, 
    RouterModule,
    FormsModule
  ]
})
export class PharmacistModule { }
