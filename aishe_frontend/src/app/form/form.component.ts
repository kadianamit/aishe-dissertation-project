import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { AuthService } from '../service/auth.service';
import { SharedService } from '../shared/shared.service';
import { LocalserviceService } from '../service/localservice.service';

@Component({
  selector: 'app-form',
  templateUrl: './form.component.html',
  styleUrls: ['./form.component.scss']
})
export class FormComponent implements OnInit {
  userId: any;
  encrypted: any;

  constructor(public router: Router, public route: ActivatedRoute, public authService: AuthService, public sharedService: SharedService,public localService:LocalserviceService) {
    if (sessionStorage.length != 0) {
      this.localService.clearData();
    }
    if (!sessionStorage.getItem('token')) {
      this.sharedService.global_loader = true;
      this.userId = this.route.snapshot.paramMap.get('id');
      this.encrypted = this.route.snapshot.paramMap.get('encrypted');
      this.getTokenDetails(this.userId, this.encrypted)
    }
  }

  ngOnInit(): void {
  }
  getTokenDetails(userId: any, encrypted: any) {
    this.authService.getToken(userId, encrypted).subscribe(res => {
      let getToken = res.token;
      let tokenarray = getToken.split('Bearer ');
      let token = tokenarray[tokenarray.length - 1];
      sessionStorage.setItem('token', token);
      this.getUserDetails()
    }, err => {
      this.sharedService.global_loader = false;
    })
  }
  getUserDetails() {
    this.authService.getUser(this.userId).subscribe((res: any) => {
      if (res.status === 200) {
        let userDetails = {
          addressLine1: res.data.addressLine1,
          addressLine2: res.data.addressLine2,
          email: res.data.email,
          firstName: res.data.firstName,
          middleName: res.data.middleName ? res.data.middleName : '',
          lastName: res.data.lastName ? res.data.lastName : '',
          mobile: res.data.mobile,
          roleId: res.data.roleId,
          stateCode: res.data.stateId,
          stateName: res.data.stateName,
          userId: res.data.userId,
          userType: res.data.userType

        };
        this.sharedService.setUserDetails(userDetails);
        this.localService.saveData('userType', res.data.userType);
        this.localService.saveData('mobile', res.data.mobile);
        this.localService.saveData('email', res.data.email);
        this.localService.saveData('firstName', res.data.firstName);
        this.localService.saveData('middleName', res.data.middleName ? res.data.middleName : '');
        this.localService.saveData('lastName', res.data.lastName ? res.data.lastName : '');
        this.localService.saveData('roleId', res.data.roleId)
        this.localService.saveData('stateCode', res.data.stateId)
        this.localService.saveData('userId', res.data.userId);
        this.router.navigateByUrl('scholarshipScheme');
        this.sharedService.global_loader = false;
      }
    }, err => {
      this.sharedService.global_loader = false;
    })
  }
}
