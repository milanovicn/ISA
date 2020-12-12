  
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'homepage',
  templateUrl: './homepage.component.html'
})

export class HomePageComponent implements OnInit {
   
  constructor(private _router: Router) { }
   
    ngOnInit(): void {
        throw new Error('Method not implemented.');
    }

    func(){
      alert('clicked me');
      console.log("click");
  
    }
}
