import { Component, OnInit } from "@angular/core";
import { Router } from "@angular/router";
import { LoginService } from "app/ISA/shared/service/login.service";
import { User } from "app/ISA/shared/model/User";

@Component({
  selector: 'pharmacy-admin-navbar',
  templateUrl: './pharmacy-admin-navbar.component.html'

})

export class PharmacyAdminNavbarComponent implements OnInit {
  user: User;
  request: Request;

  constructor(private router: Router, private loginService: LoginService) {
    this.user = new User();


  }

  ngOnInit(): void {



  }


  logOut() {
    this.loginService.logout(this.request).subscribe(result => this.homepage());
  }



  homepage(){
    this.router.navigate(["/pharmac-admin-homepage"]);
}

}