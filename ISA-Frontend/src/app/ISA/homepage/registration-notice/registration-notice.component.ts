import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Login } from '../../shared/model/Login';
import { LoginService } from '../../shared/service/login.service';
import { User } from '../../shared/model/User';

@Component({
  selector: 'registration-notice',
  templateUrl: './registration-notice.component.html'
})

export class RegistrationNoticeComponent{
 

  constructor(private router: Router) {
  }

  }