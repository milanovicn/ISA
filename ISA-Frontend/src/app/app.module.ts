import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { RouterModule } from '@angular/router';


import { AppRoutingModule } from './app.routing';
import { ComponentsModule } from './components/components.module';

import { AppComponent } from './app.component';

import { DashboardComponent } from './dashboard/dashboard.component';
import { UserProfileComponent } from './user-profile/user-profile.component';
import { TableListComponent } from './table-list/table-list.component';
import { TypographyComponent } from './typography/typography.component';
import { IconsComponent } from './icons/icons.component';
import { MapsComponent } from './maps/maps.component';
import { NotificationsComponent } from './notifications/notifications.component';
import { UpgradeComponent } from './upgrade/upgrade.component';
import {
  AgmCoreModule
} from '@agm/core';
import { AdminLayoutComponent } from './layouts/admin-layout/admin-layout.component';
import { HomePageComponent } from './ISA/homepage/homepage.component';
import { LoginComponent } from './ISA/homepage/login/login.component';
import { LoginService } from './ISA/shared/service/login.service';
import { UserHomepageComponent } from './ISA/user/user-homepage/user-homepage.component';
import { UserModule } from './ISA/user/user.module';
import { UserNavbarComponent } from './ISA/user/user-navbar/user-navbar.component';
import { UserService } from './ISA/shared/service/user.service';
import { MedicineService } from './ISA/shared/service/medicine.service';
import { PharmacyAdminModule } from './ISA/pharmacy-admin/pharmacy-admin.module';
import { PharmacyAdminService } from './ISA/shared/service/pharmacy-admin.service';
import { PharmacyAdminHomepageComponent } from './ISA/pharmacy-admin/pharmacy-admin-homepage/pharmacy-admin-homepage.component';
import { PharmacyAdminHisPharmacyComponent } from './ISA/pharmacy-admin/pharmacy-admin-hispharmacy/pharmacy-admin-hispharmacy.component';
import { PharmacyService } from './ISA/shared/service/pharmacy-service';

@NgModule({
  imports: [
    BrowserAnimationsModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
    ComponentsModule,
    RouterModule,
    AppRoutingModule,
    AgmCoreModule.forRoot({
      apiKey: 'YOUR_GOOGLE_MAPS_API_KEY'
    }),
    RouterModule.forRoot([

      { path: 'homepage', component: HomePageComponent },
      { path: 'login', component: LoginComponent },
      //{path: 'user-homepage', component: UserHomepageComponent },
      { path: '', redirectTo: 'homepage', pathMatch: 'full', },
      { path: '**', redirectTo: 'homepage', pathMatch: 'full' },
    ]),
    UserModule,
    PharmacyAdminModule
  ],
  declarations: [
    AppComponent,
    AdminLayoutComponent,
    HomePageComponent,
    LoginComponent,
    UserHomepageComponent,
    //PharmacyAdminHisPharmacyComponent,
    PharmacyAdminHomepageComponent
  ],
  providers: [LoginService, UserService, MedicineService, PharmacyAdminService, PharmacyService],
  bootstrap: [AppComponent]
})
export class AppModule { }
