import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-registration-management',
  templateUrl: './registration-management.component.html',
  styleUrls: ['./registration-management.component.scss']
})
export class RegistrationManagementComponent implements OnInit {
  selectedIndex=0;
  constructor() { }

  ngOnInit(): void {
    this.tabSelected(this.selectedIndex);
   
  }
  
  tabSelected(event: any) {
   this.selectedIndex=event.index;
  }

  

}
