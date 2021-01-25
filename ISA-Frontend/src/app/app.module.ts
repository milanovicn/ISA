import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { RouterModule } from '@angular/router';
import { AppRoutingModule } from './app.routing';
import { AppComponent } from './app.component';
import {
  AgmCoreModule
} from '@agm/core';
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
import { PharmacyService } from './ISA/shared/service/pharmacy.service';
import { AllPharmaciesComponent } from './ISA/homepage/all-pharmacies/all-pharmacies.component';
import { AllMedicinesComponent } from './ISA/homepage/all-medicines/all-medicines.component';
import { PharmacyAdminHisPharmacyComponent } from './ISA/pharmacy-admin/pharmacy-admin-hispharmacy/pharmacy-admin-hispharmacy.component';
import { PharmacyService1 } from './ISA/shared/service/pharmacy-service';
import { RegistrationComponent } from './ISA/homepage/registration/registration.component';
import { RegistrationNoticeComponent } from './ISA/homepage/registration-notice/registration-notice.component';
import { DermasComponent } from './ISA/pharmacy-admin/dermas-pharmacy/dermas-pharmacy.component';
import { SystemAdminModule } from './ISA/system-admin/system-admin.module';
import { ChangePasswordComponent } from './ISA/homepage/change-password/change-password.component';
import { SupplierModule } from './ISA/supplier/supplier.module';
import { SupplierService } from './ISA/shared/service/supplier.service';
import { SystemAdminHomepageComponent } from './ISA/system-admin/system-admin-homepage/system-admin-homepage.component';
import { SupplierHomepageComponent } from './ISA/supplier/supplier-homepage/supplier-homepage.component';


@NgModule({
  imports: [
    BrowserAnimationsModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
    RouterModule,
    AppRoutingModule,
    AgmCoreModule.forRoot({
      apiKey: 'YOUR_GOOGLE_MAPS_API_KEY'
    }),
    UserModule,
    PharmacyAdminModule,
    SystemAdminModule,
    SupplierModule,
  ],
  declarations: [
    AppComponent,
    HomePageComponent,
    LoginComponent,
    UserHomepageComponent,
    PharmacyAdminHomepageComponent,
    AllPharmaciesComponent,
    AllMedicinesComponent,
    RegistrationComponent,
    RegistrationNoticeComponent,
    ChangePasswordComponent,
    SystemAdminHomepageComponent,
    SupplierHomepageComponent,   
  ],
  providers: [LoginService, UserService, MedicineService, PharmacyAdminService, PharmacyService, SupplierService],
  bootstrap: [AppComponent]
})
export class AppModule { }
