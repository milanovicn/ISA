import { Component, OnInit } from "@angular/core";
import { Router } from "@angular/router";
import { LoginService } from "app/ISA/shared/service/login.service";
import { User } from "app/ISA/shared/model/User";

@Component({
  selector: 'system-admin-navbar',
  templateUrl: './system-admin-navbar.component.html'
  
})

export class SystemAdminNavbarComponent implements OnInit{
    user: User;
    request:Request;

    constructor(private router: Router, private loginService: LoginService) {
        this.user = new User();
        
    
      }

  ngOnInit(): void {
   


  }



  homepage(){
    this.router.navigate(["/system-admin-homepage"]);
}
}