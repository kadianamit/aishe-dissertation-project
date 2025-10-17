import { Component, OnInit } from '@angular/core';
import { SharedService } from 'src/app/shared/shared.service';

@Component({
  selector: 'app-remuneration-management',
  templateUrl: './remuneration-management.component.html',
  styleUrls: ['./remuneration-management.component.scss']
})
export class RemunerationManagementComponent implements OnInit {
  role:any;
  constructor(public sharedService: SharedService) {
    this.sharedService.getUserDetails.subscribe(res => {
      if (res !== 0) {
        //this.userID = res.userId
        this.role = res.roleId.toString();

      }
    })
  }

  ngOnInit(): void {
    
  }

}
