import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { UserHomepageComponent } from './user-homepage/user-homepage.component';
import { UserProfileComponent } from './user-profile/user-profile.component';

const routes: Routes = [
 
  { path: 'user-homepage', component: UserHomepageComponent },
  { path: 'user-homepage/user-profile', component: UserProfileComponent },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class UserRoutingModule { }
