
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Login } from '../../shared/model/Login';
import { LoginService } from '../../shared/service/login.service';
import { User } from '../../shared/model/User';
import { UserService } from 'app/ISA/shared/service/user.service';

@Component({
    selector: 'registration',
    templateUrl: './registration.component.html'
})

export class RegistrationComponent implements OnInit {
    user: User;
    response: Response;
    registrationRequest: User;
    passwordCheck: string;

    constructor(private router: Router, private userService: UserService, private loginService: LoginService) {
        this.registrationRequest = new User();
        this.user = new User();
        this.passwordCheck = "";

    }

    ngOnInit() {
        this.getUser();
        this.passwordCheck = "";
    }

    register() {
        console.log(this.passwordCheck);
        if (this.registrationRequest.password == this.passwordCheck) {
            this.userService.register(this.registrationRequest).subscribe(
                {
                    next: user => {

                        this.registrationRequest = user;
                        console.log(this.user);
                        this.router.navigate(["/login"]);

                    }

                });
            if (this.user == null) {
                alert("User with this email is already registered!");
            }
        } else {
            alert("Password and repeated password don't match!");
        }

        this.refresh();
    }
    refresh() {
        window.location.reload();
    }
    getUser() {

        this.loginService.getLoggedInUser().subscribe({
            next: user => {

                this.user = user;

                console.log(this.user);

                if (this.user.userRole == "PATIENT") {
                    console.log(this.user);
                    this.router.navigate(["/user-homepage"]);

                } else if (this.user.userRole == "PHARMACY_ADMIN") {

                    if (user.prviPutLogovan == true) {
                        console.log(this.user);
                        this.router.navigate(["/pharmacy-admin-homepage/changepassword"]);
                    }
                    else {
                        console.log(this.user);

                        this.router.navigate(["/pharmacy-admin-homepage"]);
                    }
                }

            },


        });

    }

}
