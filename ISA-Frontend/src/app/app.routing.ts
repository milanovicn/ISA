import { NgModule } from '@angular/core';
import { CommonModule, } from '@angular/common';
import { BrowserModule  } from '@angular/platform-browser';
import { Routes, RouterModule } from '@angular/router';
import { HomePageComponent } from './ISA/homepage/homepage.component';
import { LoginComponent } from './ISA/homepage/login/login.component';
import { UserHomepageComponent } from './ISA/user/user-homepage/user-homepage.component';
import { UserProfileComponent } from './ISA/user/user-profile/user-profile.component';
import { UserModule } from './ISA/user/user.module';
import { PharmacyAdminModule } from './ISA/pharmacy-admin/pharmacy-admin.module';
import { AllPharmaciesComponent } from './ISA/homepage/all-pharmacies/all-pharmacies.component';
import { AllMedicinesComponent } from './ISA/homepage/all-medicines/all-medicines.component';
import { RegistrationComponent } from './ISA/homepage/registration/registration.component';
import { RegistrationNoticeComponent } from './ISA/homepage/registration-notice/registration-notice.component';
import { ChangePasswordComponent } from './ISA/homepage/change-password/change-password.component';
import { SystemAdminModule } from './ISA/system-admin/system-admin.module';
import { SupplierModule } from './ISA/supplier/supplier.module';
import { PharmacistModule } from './ISA/pharmacist/pharmacist.module';
import { DermatologistRoutingModule } from './ISA/dermatologist/dermatologist-routing.module';

const routes: Routes =[
  { path: 'homepage', component: HomePageComponent },
  { path: 'login', component: LoginComponent },
  { path: 'homepage/change-password', component: ChangePasswordComponent },
  { path: 'homepage/registration', component: RegistrationComponent },
  { path: 'homepage/registration-notice', component: RegistrationNoticeComponent },
  { path: 'homepage/pharmacies', component: AllPharmaciesComponent },
  { path: 'homepage/medicines', component: AllMedicinesComponent },
  { path: '', redirectTo: 'homepage', pathMatch: 'full',}, 
  { path: '**', redirectTo: 'homepage', pathMatch: 'full'},
 
];

@NgModule({
  imports: [
    CommonModule,
    BrowserModule,
    RouterModule.forRoot(routes,{
       useHash: true
    }),
    UserModule,
    PharmacyAdminModule,
    SystemAdminModule,
    SupplierModule,
    PharmacistModule,
    DermatologistRoutingModule
  ],
  exports: [
  ],
})
export class AppRoutingModule { }
