import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Complaint } from 'app/ISA/shared/model/Complaint';
import { Dermatologist } from 'app/ISA/shared/model/Dermatologist';
import { Pharmacist } from 'app/ISA/shared/model/Pharmacist';
import { Pharmacy } from 'app/ISA/shared/model/Pharmacy';
import { User } from 'app/ISA/shared/model/User';
import { LoginService } from 'app/ISA/shared/service/login.service';
import { PharmacyAdminService } from 'app/ISA/shared/service/pharmacy-admin.service';
import { SystemAdminService } from 'app/ISA/shared/service/system-admin.service';
import { UserService } from 'app/ISA/shared/service/user.service';


@Component({
    templateUrl: './complaints.component.html'
})


export class ComplaintsComponent implements OnInit {
    unansweredComplaints: Complaint[] = [];
    user: User;
    ret: Object = new Object();

    constructor(private router: Router, private systemAdminService: SystemAdminService, private loginService: LoginService) {
        this.user = new User();
      
    }

    ngOnInit(): void {
        this.getUser();
    }


    getUser() {
        this.loginService.getLoggedInUser().subscribe({
            next: user => {
                this.user = user;
                this.fillData();
            }
        });
    }

    fillData() {
        
        this.systemAdminService.getUnanswered().subscribe({
            next: unansweredComplaints => {
                this.unansweredComplaints = unansweredComplaints;  
            }
        });

    }

    answer(complaint : Complaint) {
        console.log(complaint);
         this.systemAdminService.answerComplaint(complaint[0].id, complaint[0].complaintAnswer).subscribe({
            next: ret => {
                this.ret = ret;
                console.log(this.ret);
                if(this.ret==null){
                    alert("Complaint answering unsuccessfull!");
                } else {
                    alert("Complaint answering successfully sent!");
                }
                this.refresh();
            }

        }); 
    }

    refresh() {
        window.location.reload();
    }



}