import { Component, OnInit } from '@angular/core';
import { UserManagementService } from '../user-management/user-management.service';
import { SharedService } from 'src/app/shared/shared.service';

@Component({
  selector: 'app-moe-management',
  templateUrl: './moe-management.component.html',
  styleUrls: ['./moe-management.component.scss'],
})
export class MoeManagementComponent implements OnInit {
  userList: Array<any> = [];
  userId: any;
  activeMoeUser: any;
  userArr: Array<any> = [];
  constructor(public userService: UserManagementService,public sharedService:SharedService) {
    this.getuserList();
  }

  ngOnInit(): void {}
  getuserList() {
    let payload = {
      roleId: '1',
      stateCode: null,
      isApproved: true,
      userStatus: 'USER_APPROVED',
    };
    this.userService.getUserListLevel1(payload).subscribe(
      (res) => {
        this.userArr = res.data;
        this.userList = this.userArr.slice();
      },
      (err) => {}
    );
  }
  save() {
    this.userService
      .activeInactiveMoE(this.userId, this.activeMoeUser)
      .subscribe(
        (res) => {
          if (res.status === 201) {
            this.sharedService.showSuccess()
          }
        },
        (err) => {}
      );
  }
}
