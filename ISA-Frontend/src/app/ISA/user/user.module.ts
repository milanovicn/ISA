import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { UserRoutingModule } from './user-routing.module';
import { UserProfileComponent } from './user-profile/user-profile.component';
import { RouterModule } from '@angular/router';
import { UserNavbarComponent } from './user-navbar/user-navbar.component';
import { FormsModule } from '@angular/forms';


@NgModule({
  declarations: [UserProfileComponent, UserNavbarComponent ],
  imports: [
    CommonModule,
    UserRoutingModule, 
    RouterModule,
    FormsModule
  ]
})
export class UserModule { }
