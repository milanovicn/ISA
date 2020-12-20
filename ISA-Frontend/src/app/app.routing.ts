import { NgModule } from '@angular/core';
import { CommonModule, } from '@angular/common';
import { BrowserModule  } from '@angular/platform-browser';
import { Routes, RouterModule } from '@angular/router';

import { AdminLayoutComponent } from './layouts/admin-layout/admin-layout.component';
import { HomePageComponent } from './ISA/homepage/homepage.component';
import { LoginComponent } from './ISA/homepage/login/login.component';
import { UserHomepageComponent } from './ISA/user/user-homepage/user-homepage.component';
import { UserProfileComponent } from './ISA/user/user-profile/user-profile.component';
import { UserModule } from './ISA/user/user.module';
import { PharmacyAdminModule } from './ISA/pharmacy-admin/pharmacy-admin.module';

const routes: Routes =[
  { path: 'homepage', component: HomePageComponent },
  { path: 'login', component: LoginComponent },
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
    PharmacyAdminModule
  ],
  exports: [
  ],
})
export class AppRoutingModule { }
