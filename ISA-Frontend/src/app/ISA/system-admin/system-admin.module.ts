import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { SystemAdminRoutingModule } from './system-admin-routing.module';
import { SystemAdminNavbarComponent } from './system-admin-navbar/system-admin-navbar.component';


@NgModule({
  declarations: [SystemAdminNavbarComponent],
  imports: [
    CommonModule,
    SystemAdminRoutingModule, 
    RouterModule,
    FormsModule
  ]
})
export class SystemAdminModule { }
